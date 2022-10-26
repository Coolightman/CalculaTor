package by.coolightman.calculator.ui.screens.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.coolightman.calculator.data.HistoryRowRepository
import by.coolightman.calculator.model.CalculatorAction
import by.coolightman.calculator.model.CalculatorAddOperation
import by.coolightman.calculator.model.CalculatorNumber
import by.coolightman.calculator.model.CalculatorOperation
import by.coolightman.calculator.model.HistoryRow
import by.coolightman.calculator.ui.models.ThemeMode
import by.coolightman.calculator.util.DECIMAL_SEPARATOR
import by.coolightman.calculator.util.ERROR_MESSAGE
import by.coolightman.calculator.util.THEME_MODE_KEY
import by.coolightman.calculator.util.formatResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import java.math.BigInteger
import javax.inject.Inject
import kotlin.math.cos
import kotlin.math.log10
import kotlin.math.log2
import kotlin.math.roundToLong
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val historyRowRepository: HistoryRowRepository
) : ViewModel() {

    var uiState by mutableStateOf(CalculatorUiState())
        private set

    private var result: String = ""
    private var displayedFormula = ""

    private val operations = listOf(
        CalculatorOperation.Plus.symbol,
        CalculatorOperation.Minus.symbol,
        CalculatorOperation.Divide.symbol,
        CalculatorOperation.Multiply.symbol,
        CalculatorOperation.Exp.symbol
    )

    private val operationsRegex = "[-+÷×^]".toPattern()

    init {
        getThemePreference()
    }

    fun onAction(action: CalculatorAction) {
        checkError()
        when (action) {
            is CalculatorOperation -> enterOperation(action)
            is CalculatorNumber -> enterNumber(action.number)
            is CalculatorAction.Backspace -> performBackspace()
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Clear -> clearState()
            is CalculatorAction.Equal -> performEqual()
            is CalculatorAddOperation -> performAddOperation(action)
        }
        calculateIfNeed(action)
        refreshState()
    }

    fun saveThemePreference(value: Boolean) {
        viewModelScope.launch {
            val dataStoreKey = booleanPreferencesKey(THEME_MODE_KEY)
            dataStore.edit { preferences ->
                preferences[dataStoreKey] = value
            }
        }
    }

    private fun getThemePreference() {
        viewModelScope.launch {
            val dataStoreKey = booleanPreferencesKey(THEME_MODE_KEY)
            dataStore.data.map { preferences -> preferences[dataStoreKey] ?: true }
                .collectLatest { pref ->
                    uiState = uiState.copy(
                        themeModePreference = if (pref) ThemeMode.DARK_MODE
                        else ThemeMode.LIGHT_MODE
                    )
                }
        }
    }

    private fun performAddOperation(action: CalculatorAddOperation) {
        if (displayedFormula.isEmpty()) return

        val values = displayedFormula
            .trimStart('-')
            .split(operationsRegex)
            .filter { it != "" }

        if (values.size == 1) {
            val countingValue = if (displayedFormula.startsWith('-')) {
                '-' + values[0]
            } else values[0]
            val number = countingValue.toDouble()
            viewModelScope.launch {
                val deferred = async(Dispatchers.Default) {
                    when (action) {
                        is CalculatorAddOperation.Sin -> calculateSin(
                            number
                        )
                        is CalculatorAddOperation.Cos -> calculateCos(
                            number
                        )
                        is CalculatorAddOperation.Tg -> calculateTg(
                            number
                        )
                        is CalculatorAddOperation.Ctg -> calculateCtg(
                            number
                        )
                        is CalculatorAddOperation.Sqrt -> calculateSqrt(
                            number
                        )
                        is CalculatorAddOperation.Round -> calculateRound(
                            number
                        )
                        is CalculatorAddOperation.Percent -> calculatePercent(
                            number
                        )
                        is CalculatorAddOperation.OneDivide -> calculateOneDivide(
                            number
                        )
                        is CalculatorAddOperation.Lg -> calculateLg(
                            number
                        )
                        is CalculatorAddOperation.Log2 -> calculateLog2(
                            number
                        )
                        is CalculatorAddOperation.Factorial -> calculateFactorial(
                            number
                        )
                    }
                }
                try {
                    displayedFormula = deferred.await().formatResult()
                } catch (e: Exception) {
                    result = ERROR_MESSAGE
                }
                refreshState()
            }
        }
    }

    private fun calculateFactorial(number: Double): String {
        var factorial = BigInteger.ONE
        for (i in 1..number.toInt()) {
            factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
        }
        return factorial.toString()
    }

    private fun calculateRound(number: Double): String {
        return number.roundToLong().toString()
    }

    private fun calculateOneDivide(number: Double): String {
        return (1 / number).toString()
    }

    private fun calculateLog2(number: Double): String {
        return log2(number).toString()
    }

    private fun calculateLg(number: Double): String {
        return log10(number).toString()
    }

    private fun calculatePercent(number: Double): String {
        return (number * 100).toString()
    }

    private fun calculateSqrt(number: Double): String {
        return sqrt(number).toString()
    }

    private fun calculateCtg(number: Double): String {
        return (1 / tan((Math.PI / 180) * number)).toString()
    }

    private fun calculateTg(number: Double): String {
        return tan((Math.PI / 180) * number).toString()
    }

    private fun calculateCos(number: Double): String {
        return if (number == 90.0) "0"
        else cos((Math.PI / 180) * number).toString()
    }

    private fun calculateSin(number: Double): String {
        return sin((Math.PI / 180) * number).toString()
    }

    private fun calculateIfNeed(action: CalculatorAction) {
        if (action is CalculatorNumber || action is CalculatorAction.Backspace) {
            calculate()
        }
    }

    private fun calculate() {
        val valueToCheck = displayedFormula.trimStart('-')
        val values = valueToCheck.split(operationsRegex).filter { it != "" }
        if (values.isEmpty() || values.size == 1) {
            result = ""
            return
        }
        if (lastCharIsOperation()) return

        val formula = displayedFormula
            .replace(CalculatorOperation.Divide.symbol, "/")
            .replace(CalculatorOperation.Multiply.symbol, "*")

        val expression = ExpressionBuilder(formula).build()

        result = try {
            val calcResult = expression.evaluate().toString()
            calcResult.formatResult()
        } catch (e: Exception) {
            ERROR_MESSAGE
        }
    }

    private fun lastCharIsOperation(): Boolean {
        val lastChar = getFormulaLastChar()
        lastChar?.let {
            return operations.contains(it.toString())
        } ?: return false
    }

    private fun clearState() {
        result = ""
        displayedFormula = ""
    }

    private fun checkError() {
        if (uiState.mainText == ERROR_MESSAGE) clearState()
    }


    private fun refreshState() {
        uiState = uiState.copy(
            mainText = displayedFormula,
            secondText = result
        )
    }

    private fun performEqual() {
        displayedFormula = if (result != ERROR_MESSAGE) {
            saveHistory()
            result
        } else ""
        result = ""
    }

    private fun saveHistory() {
        viewModelScope.launch {
            if (displayedFormula.isNotEmpty() && result.isNotEmpty()) {
                val row = HistoryRow(
                    expression = displayedFormula,
                    result = result
                )
                historyRowRepository.insert(row)
            }
        }
    }

    private fun performBackspace() {
        val lastChar = getFormulaLastChar()
        lastChar?.let {
            displayedFormula = if (lastCharIsENumber(it) || displayedFormula.contains("E-")) {
                val index = displayedFormula.indexOf("E")
                val drop = displayedFormula.length - index
                displayedFormula.dropLast(drop)
            } else {
                displayedFormula.dropLast(1)
            }
        }
    }

    private fun lastCharIsENumber(it: Char) = lastCharIsNumber(it) && lastNumberIncludeE()

    private fun lastCharIsNumber(it: Char) = !operations.contains(it.toString())

    private fun lastNumberIncludeE(): Boolean {
        val values = displayedFormula
            .trimStart('-')
            .split(operationsRegex)
            .filter { it != "" }

        val lastNumber = values.takeLast(1)
        return lastNumber[0].contains("E")
    }

    private fun getFormulaLastChar() = displayedFormula.lastOrNull()

    private fun enterOperation(operation: CalculatorOperation) {
        val lastChar = getFormulaLastChar()
        lastChar?.let {
            if (it.toString() == DECIMAL_SEPARATOR) {
                displayedFormula = displayedFormula.dropLast(1)
                displayedFormula += operation.symbol
            } else if (operations.contains(it.toString()) && displayedFormula.length > 1) {
                if (operation is CalculatorOperation.Minus && lastChar == '^') {
                    displayedFormula += operation.symbol
                } else {
                    displayedFormula = displayedFormula.dropLast(1)
                    displayedFormula += operation.symbol
                }
            } else if (it.isDigit()) {
                displayedFormula += operation.symbol
            }
            return
        }

        if (operation is CalculatorOperation.Minus) {
            displayedFormula += operation.symbol
        }
    }

    private fun enterDecimal() {
        val value = getLastValue()
        if (!value.contains(DECIMAL_SEPARATOR)) {
            displayedFormula += when {
                value.isEmpty() -> "0$DECIMAL_SEPARATOR"
                else -> DECIMAL_SEPARATOR
            }
        }
    }

    private fun getLastValue(): String {
        val valueToCheck = displayedFormula.trimStart('-')
        return valueToCheck.substring(valueToCheck.lastIndexOfAny(operations) + 1)
    }

    private fun enterNumber(number: Int) {
        if (number == 0) checkDoubleZero()
        else addNumber(number)
    }

    private fun addNumber(number: Int) {
        displayedFormula += number
    }

    private fun checkDoubleZero() {
        val value = getLastValue()
        if (value != "0" || value.contains(DECIMAL_SEPARATOR)) {
            addNumber(0)
        }
    }
}
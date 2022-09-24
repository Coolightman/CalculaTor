package com.example.calculator.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.calculator.model.CalculatorAction
import com.example.calculator.model.CalculatorNumber
import com.example.calculator.model.CalculatorOperation
import com.example.calculator.model.MainScreenState
import com.example.calculator.util.DECIMAL_SEPARATOR
import com.example.calculator.util.ERROR_MESSAGE
import com.example.calculator.util.formatResult
import net.objecthunter.exp4j.ExpressionBuilder

class MainViewModel : ViewModel() {

    var state by mutableStateOf(MainScreenState())
        private set

    private var result: String = ""
    private var displayedFormula = ""

    private val operations = listOf(
        CalculatorOperation.Plus.symbol,
        CalculatorOperation.Minus.symbol,
        CalculatorOperation.Divide.symbol,
        CalculatorOperation.Multiply.symbol
    )

    private val operationsRegex = "[-+÷×]".toPattern()

    fun onAction(action: CalculatorAction) {
        checkError()
        when (action) {
            is CalculatorOperation -> enterOperation(action)
            is CalculatorNumber -> enterNumber(action.number)
            is CalculatorAction.Backspace -> performBackspace()
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Clear -> clearState()
            is CalculatorAction.Equal -> performEqual()
            else -> Log.d("MainViewModel", "else operation")
        }
        calculateIfNeed(action)
        refreshState()
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
        if (state.mainText == ERROR_MESSAGE) clearState()
    }


    private fun refreshState() {
        state = state.copy(
            mainText = displayedFormula,
            secondText = result
        )
    }

    private fun performEqual() {
        displayedFormula = if (result != ERROR_MESSAGE) {
            result
        } else ""
        result = ""
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
                displayedFormula = displayedFormula.dropLast(1)
                displayedFormula += operation.symbol
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
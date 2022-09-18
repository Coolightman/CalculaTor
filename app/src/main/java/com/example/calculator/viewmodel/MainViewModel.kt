package com.example.calculator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.calculator.model.CalculatorAction
import com.example.calculator.model.CalculatorNumber
import com.example.calculator.model.CalculatorOperation
import com.example.calculator.model.MainScreenState
import com.example.calculator.util.RESULT_MAX_LENGTH
import net.objecthunter.exp4j.ExpressionBuilder
import java.math.BigDecimal
import java.text.DecimalFormat

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
            formatResult(calcResult)
        } catch (e: Exception) {
            ERROR_MESSAGE
        }
    }

    private fun formatResult(calcResult: String): String {
        var formatResult = ""

//        expand any result to non E format
        val bd = BigDecimal(calcResult)
        formatResult = bd.toPlainString()

//        set to Integer if possible
        formatResult = if (formatResult.substring(formatResult.length - 2) == ".0") {
            formatResult.dropLast(2)
        } else {
            formatResult
        }

        val decimalIndex = formatResult.indexOf(DECIMAL_SEPARATOR)
        val resultLength = formatResult.length

//        if (decimalIndex != -1) {
//            formatResult = checkException(formatResult)
//        }

        when {
            decimalIndex == -1 -> {
//                format Integer result or little Double result
                if (resultLength > RESULT_MAX_LENGTH) {
                    val dec = DecimalFormat("0.######E0")
                    formatResult = dec.format(formatResult.toDouble())
                }
            }
            decimalIndex == 1 && formatResult[0] == '0' -> {
//                format little Double result
                if (resultLength > RESULT_MAX_LENGTH) {
                    val dec = DecimalFormat("0.#####E0")
                    formatResult = dec.format(formatResult.toDouble())
                }
            }
            decimalIndex != -1 -> {
//                format Double result
                return calcResult

            }
        }

        return formatResult
    }

//    private fun checkException(formatResult: String): String {
//        var checkedResult = ""
//        val afterDecimal = formatResult.substring(formatResult.indexOf(DECIMAL_SEPARATOR))
//        val beforeDecimal = formatResult.substring(0, formatResult.indexOf(DECIMAL_SEPARATOR))
//        if (afterDecimal.length < 5) {
//            return formatResult
//        }
//
//        val lastIndex = afterDecimal.length - 1
//
////        check exception
//        if (afterDecimal[lastIndex - 1] == '0' && afterDecimal[lastIndex - 2] == '0') {
//            val exceptionBeginIndex = findExceptionBeginIndex(afterDecimal)
//            val cutAfterDecimal = afterDecimal.substring(0, exceptionBeginIndex)
//            checkedResult = beforeDecimal + DECIMAL_SEPARATOR + cutAfterDecimal
//        } else return formatResult
//
//        return checkedResult
//    }
//
//    private fun findExceptionBeginIndex(row: String): Int {
//        var result = row.length - 2
//        for (i in row.length - 3 downTo 0) {
//            if (row[i] == '0') {
//                result = i
//            } else break
//        }
//        return result
//    }

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
            displayedFormula = displayedFormula.dropLast(1)
        }
    }

    private fun getFormulaLastChar() = displayedFormula.lastOrNull()

    private fun enterOperation(operation: CalculatorOperation) {
        val lastChar = getFormulaLastChar()
        lastChar?.let {
            if (it.toString() == DECIMAL_SEPARATOR) {
                displayedFormula = displayedFormula.dropLast(1)
            } else if (operations.contains(it.toString()) && displayedFormula.length > 1) {
                displayedFormula = displayedFormula.dropLast(1)
                displayedFormula += operation.symbol
            } else if (displayedFormula.length > 1) {
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

    companion object {
        private const val ERROR_MESSAGE = "error"
        private const val DECIMAL_SEPARATOR = "."
    }
}
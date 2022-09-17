package com.example.calculator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.calculator.model.CalculatorAction
import com.example.calculator.model.CalculatorOperation
import com.example.calculator.model.MainScreenState
import com.example.calculator.util.toIntOrNull

class MainViewModel : ViewModel() {

    private var number1: String = ""
    private var number2: String = ""
    private var operation: CalculatorOperation? = null

    var state by mutableStateOf(MainScreenState())
        private set

    fun onAction(action: CalculatorAction) {
        checkError()
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Clear -> clearState()
            is CalculatorOperation -> enterOperation(action)
            is CalculatorAction.Equal -> performEqual()
            is CalculatorAction.Backspace -> performBackspace()
        }
        refreshState()
    }

    private fun checkError() {
        if (number1 == ERROR_MESSAGE) clearState()
    }

    private fun clearState() {
        number1 = ""
        number2 = ""
        operation = null
    }

    private fun refreshState() {
        state = state.copy(
            mainText = number1 + (operation?.symbol ?: "") + number2
        )
    }

    private fun performEqual() {
        val number1Double = number1.toDoubleOrNull()
        val number2Double = number2.toDoubleOrNull()
        if (number1Double != null && number2Double != null) {
            val result = when (operation) {
                is CalculatorOperation.Plus -> number1Double + number2Double
                is CalculatorOperation.Minus -> number1Double - number2Double
                is CalculatorOperation.Multiply -> number1Double * number2Double
                is CalculatorOperation.Divide -> {
                    if (number2Double != 0.0) {
                        number1Double / number2Double
                    } else null
                }
                null -> return
            }
            clearState()
            number1 = result?.let { (it.toIntOrNull() ?: it).toString().take(15) } ?: ERROR_MESSAGE
        }
    }

    private fun performBackspace() {
        when {
            number2.isNotEmpty() -> number2 = number2.dropLast(1)
            operation != null -> operation = null
            number1.isNotEmpty() -> number1 = number1.dropLast(1)
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (number1.isNotEmpty()) {
            this.operation = operation
        }
    }

    private fun enterDecimal() {
        if (operation == null && !number1.contains(".") && number1.isNotEmpty()) {
            number1 += "."
        } else if (!number2.contains(".") && number2.isNotEmpty()) {
            number2 += "."
        }
    }

    private fun enterNumber(number: Int) {
        if (operation == null) {
            if (number1.length >= MAX_NUM_LENGTH) {
                return
            }
            number1 += number
        } else {
            if (number2.length >= MAX_NUM_LENGTH) {
                return
            }
            number2 += number
        }
    }

    companion object {
        private const val MAX_NUM_LENGTH = 10
        private const val ERROR_MESSAGE = "error"
    }
}
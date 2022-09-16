package com.example.calculator.model

sealed class CalculatorAction{
    data class Number(val number: Int): CalculatorAction()
    object Clear: CalculatorAction()
    object Backspace: CalculatorAction()
    object Decimal: CalculatorAction()
    object Equal: CalculatorAction()
}

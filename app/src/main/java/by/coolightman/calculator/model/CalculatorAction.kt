package by.coolightman.calculator.model

sealed class CalculatorAction {
    object Clear : CalculatorAction()
    object Backspace : CalculatorAction()
    object Decimal : CalculatorAction()
    object Equal : CalculatorAction()
}

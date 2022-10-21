package by.coolightman.calculator.model

sealed class CalculatorOperation(val symbol: String) : CalculatorAction() {
    object Plus : CalculatorOperation("+")
    object Minus : CalculatorOperation("-")
    object Multiply : CalculatorOperation("\u00D7")
    object Divide : CalculatorOperation("\u00F7")
    object Exp : CalculatorOperation("^")
}

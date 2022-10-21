package by.coolightman.calculator.model

sealed class CalculatorAddOperation() : CalculatorAction() {
    object Sin : CalculatorAddOperation()
    object Cos : CalculatorAddOperation()
    object Tg : CalculatorAddOperation()
    object Ctg : CalculatorAddOperation()
    object Sqrt : CalculatorAddOperation()
    object Factorial : CalculatorAddOperation()
    object Round : CalculatorAddOperation()
    object Log2 : CalculatorAddOperation()
    object Lg : CalculatorAddOperation()
    object Percent : CalculatorAddOperation()
    object OneDivide : CalculatorAddOperation()
}

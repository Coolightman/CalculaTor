package by.coolightman.calculator

import by.coolightman.calculator.model.CalculatorAction
import by.coolightman.calculator.model.CalculatorNumber
import by.coolightman.calculator.model.CalculatorOperation
import by.coolightman.calculator.viewmodel.MainViewModel
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel()
    }

    @After
    fun cleanUp(){
//        some clean operations
    }

    @Test
    fun onActionDecimalInEmpty(){
        viewModel.onAction(CalculatorAction.Decimal)
        val expected = "0."
        val actual = viewModel.state.mainText
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun onActionDecimalAfterNumber(){
        with(viewModel){
            onAction(CalculatorNumber(6))
            onAction(CalculatorAction.Decimal)
        }
        val expected = "6."
        val actual = viewModel.state.mainText
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun onActionDecimalAfterOperation(){
        with(viewModel){
            onAction(CalculatorNumber(6))
            onAction(CalculatorOperation.Plus)
            onAction(CalculatorAction.Decimal)
        }
        val expected = "6+0."
        val actual = viewModel.state.mainText
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun onActionDecimalAfterDecimal(){
        with(viewModel){
            onAction(CalculatorAction.Decimal)
            onAction(CalculatorAction.Decimal)
        }
        val expected = "0."
        val actual = viewModel.state.mainText
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun onActionEqualAfterDecimal(){
        with(viewModel) {
            onAction(CalculatorAction.Decimal)
            onAction(CalculatorAction.Equal)
        }
        val expected = ""
        val actual = viewModel.state.mainText
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun onActionClearAny(){
        with(viewModel) {
            onAction(CalculatorAction.Decimal)
            onAction(CalculatorNumber(6))
            onAction(CalculatorOperation.Plus)
            onAction(CalculatorAction.Clear)
        }
        val expected = ""
        val actual1 = viewModel.state.mainText
        val actual2 = viewModel.state.secondText
        Assert.assertEquals(expected, actual1)
        Assert.assertEquals(expected, actual2)
    }
}
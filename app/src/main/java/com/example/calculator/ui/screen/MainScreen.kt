package com.example.calculator.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.calculator.model.CalculatorAction
import com.example.calculator.model.CalculatorOperation
import com.example.calculator.model.CalculatorState
import com.example.calculator.ui.compose.*

@Composable
fun MainScreen(
    state: CalculatorState,
    onAction: (CalculatorAction) -> Unit
) {
    var mainText by remember {
        mutableStateOf("")
    }
    val secondText by remember {
        mutableStateOf("")
    }
    mainText = state.number1 + (state.operation?.symbol ?: "") + state.number2
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MainRow(mainText)
        SecondRaw(secondText)
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp))
                .background(MaterialTheme.colors.surface)
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                ButtonClear { onAction(CalculatorAction.Clear) }
                ButtonNumber(number = "7") { onAction(CalculatorAction.Number(7)) }
                ButtonNumber(number = "4") { onAction(CalculatorAction.Number(4)) }
                ButtonNumber(number = "1") { onAction(CalculatorAction.Number(1)) }
                CustomButton(symbol = "%") { }
            }

            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                DivideButton { onAction(CalculatorOperation.Divide) }
                ButtonNumber(number = "8") { onAction(CalculatorAction.Number(8)) }
                ButtonNumber(number = "5") { onAction(CalculatorAction.Number(5)) }
                ButtonNumber(number = "2") { onAction(CalculatorAction.Number(2)) }
                ButtonNumber(number = "0") { onAction(CalculatorAction.Number(0)) }
            }

            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                ButtonMultiply { onAction(CalculatorOperation.Multiply) }
                ButtonNumber(number = "9") { onAction(CalculatorAction.Number(9)) }
                ButtonNumber(number = "6") { onAction(CalculatorAction.Number(6)) }
                ButtonNumber(number = "3") { onAction(CalculatorAction.Number(3)) }
                CustomButton(symbol = ".") { onAction(CalculatorAction.Decimal) }
            }

            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                ButtonBackspace { onAction(CalculatorAction.Backspace) }
                ButtonMinus { onAction(CalculatorOperation.Minus) }
                ButtonPlus { onAction(CalculatorOperation.Plus) }
                ButtonEqual { onAction(CalculatorAction.Equal) }
            }
        }
    }
}
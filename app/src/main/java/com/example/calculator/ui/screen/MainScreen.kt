package com.example.calculator.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.calculator.model.CalculatorAction
import com.example.calculator.model.CalculatorNumber
import com.example.calculator.model.CalculatorOperation
import com.example.calculator.model.MainScreenState
import com.example.calculator.ui.compose.*

@Composable
fun MainScreen(
    state: MainScreenState,
    onAction: (CalculatorAction) -> Unit
) {
    Box(contentAlignment = Alignment.BottomCenter){
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            MainRow(state.mainText)
            SecondRaw(state.secondText)
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp))
                    .background(MaterialTheme.colors.surface)
                    .padding(24.dp)
            ) {
                Column {
                    ButtonClear { onAction(CalculatorAction.Clear) }
                    ButtonNumber(number = "7") { onAction(CalculatorNumber(7)) }
                    ButtonNumber(number = "4") { onAction(CalculatorNumber(4)) }
                    ButtonNumber(number = "1") { onAction(CalculatorNumber(1)) }
                    Spacer(modifier = Modifier.height(83.dp))
                }

                Column {
                    DivideButton { onAction(CalculatorOperation.Divide) }
                    ButtonNumber(number = "8") { onAction(CalculatorNumber(8)) }
                    ButtonNumber(number = "5") { onAction(CalculatorNumber(5)) }
                    ButtonNumber(number = "2") { onAction(CalculatorNumber(2)) }
                    ButtonNumber(number = "0") { onAction(CalculatorNumber(0)) }
                }

                Column {
                    ButtonMultiply { onAction(CalculatorOperation.Multiply) }
                    ButtonNumber(number = "9") { onAction(CalculatorNumber(9)) }
                    ButtonNumber(number = "6") { onAction(CalculatorNumber(6)) }
                    ButtonNumber(number = "3") { onAction(CalculatorNumber(3)) }
                    CustomButton(symbol = ".") { onAction(CalculatorAction.Decimal) }
                }

                Column {
                    ButtonBackspace { onAction(CalculatorAction.Backspace) }
                    ButtonMinus { onAction(CalculatorOperation.Minus) }
                    ButtonPlus { onAction(CalculatorOperation.Plus) }
                    ButtonEqual { onAction(CalculatorAction.Equal) }
                }
            }
        }
    }
}
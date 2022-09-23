package com.example.calculator.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.example.calculator.model.CalculatorAction
import com.example.calculator.model.CalculatorNumber
import com.example.calculator.model.CalculatorOperation
import com.example.calculator.util.CORNER_SHAPE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Keyboard(
    scope: CoroutineScope,
    sheetState: BottomSheetState,
    modifier: Modifier = Modifier,
    onAction: (CalculatorAction) -> Unit
) {
    var keyboardHeightPx by remember {
        mutableStateOf(0)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom,
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp)
            .clip(RoundedCornerShape(topStart = CORNER_SHAPE.dp, topEnd = CORNER_SHAPE.dp))
            .background(MaterialTheme.colors.surface)
            .onGloballyPositioned { coordinates ->
                keyboardHeightPx = coordinates.size.height
            }
    ) {

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            ButtonClear { onAction(CalculatorAction.Clear) }
            ButtonNumber(number = "7") { onAction(CalculatorNumber(7)) }
            ButtonNumber(number = "4") { onAction(CalculatorNumber(4)) }
            ButtonNumber(number = "1") { onAction(CalculatorNumber(1)) }
            ButtonExpandBottomSheet {
                scope.launch {
                    if (sheetState.isCollapsed) {
                        sheetState.expand()
                    }
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            DivideButton { onAction(CalculatorOperation.Divide) }
            ButtonNumber(number = "8") { onAction(CalculatorNumber(8)) }
            ButtonNumber(number = "5") { onAction(CalculatorNumber(5)) }
            ButtonNumber(number = "2") { onAction(CalculatorNumber(2)) }
            ButtonNumber(number = "0") { onAction(CalculatorNumber(0)) }
        }

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            ButtonMultiply { onAction(CalculatorOperation.Multiply) }
            ButtonNumber(number = "9") { onAction(CalculatorNumber(9)) }
            ButtonNumber(number = "6") { onAction(CalculatorNumber(6)) }
            ButtonNumber(number = "3") { onAction(CalculatorNumber(3)) }
            CustomButton(symbol = ".") { onAction(CalculatorAction.Decimal) }
        }

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            ButtonBackspace { onAction(CalculatorAction.Backspace) }
            ButtonMinus { onAction(CalculatorOperation.Minus) }
            ButtonPlus { onAction(CalculatorOperation.Plus) }
            ButtonEqual(keyboardHeightPx) { onAction(CalculatorAction.Equal) }
        }
    }
}
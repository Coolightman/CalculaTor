package by.coolightman.calculator.ui.components

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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import by.coolightman.calculator.model.CalculatorAction
import by.coolightman.calculator.model.CalculatorNumber
import by.coolightman.calculator.model.CalculatorOperation
import by.coolightman.calculator.util.CORNER_SHAPE
import by.coolightman.calculator.util.pxToDp
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
    val density = LocalDensity.current
    var keyboardHeightDp by remember {
        mutableStateOf(0.dp)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
            .height(420.dp)
            .clip(RoundedCornerShape(topStart = CORNER_SHAPE.dp, topEnd = CORNER_SHAPE.dp))
            .background(MaterialTheme.colors.surface)
            .onGloballyPositioned { coordinates ->
                keyboardHeightDp = derivedStateOf { coordinates.size.height.pxToDp(density) }.value
            }
            .padding(12.dp, 0.dp, 12.dp, 0.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
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

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            DivideButton { onAction(CalculatorOperation.Divide) }
            ButtonNumber(number = "8") { onAction(CalculatorNumber(8)) }
            ButtonNumber(number = "5") { onAction(CalculatorNumber(5)) }
            ButtonNumber(number = "2") { onAction(CalculatorNumber(2)) }
            ButtonNumber(number = "0") { onAction(CalculatorNumber(0)) }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            ButtonMultiply { onAction(CalculatorOperation.Multiply) }
            ButtonNumber(number = "9") { onAction(CalculatorNumber(9)) }
            ButtonNumber(number = "6") { onAction(CalculatorNumber(6)) }
            ButtonNumber(number = "3") { onAction(CalculatorNumber(3)) }
            CustomButton(symbol = ".") { onAction(CalculatorAction.Decimal) }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            ButtonBackspace { onAction(CalculatorAction.Backspace) }
            ButtonMinus { onAction(CalculatorOperation.Minus) }
            ButtonPlus { onAction(CalculatorOperation.Plus) }
            ButtonEqual(keyboardHeightDp) { onAction(CalculatorAction.Equal) }
        }
    }
}
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
    val btHeight by remember {
        derivedStateOf { (keyboardHeightDp.value * 0.8 / 5).dp }
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .clip(RoundedCornerShape(topStart = CORNER_SHAPE.dp, topEnd = CORNER_SHAPE.dp))
            .background(MaterialTheme.colors.surface)
            .onGloballyPositioned { coordinates ->
                keyboardHeightDp = derivedStateOf { coordinates.size.height.pxToDp(density) }.value
            }
            .padding(horizontal = 12.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            ButtonClear(
                height = btHeight,
            ) { onAction(CalculatorAction.Clear) }

            ButtonNumber(
                height = btHeight,
                number = "7"
            ) { onAction(CalculatorNumber(7)) }

            ButtonNumber(
                height = btHeight,
                number = "4"
            ) { onAction(CalculatorNumber(4)) }

            ButtonNumber(
                height = btHeight,
                number = "1"
            ) { onAction(CalculatorNumber(1)) }

            ButtonExpandBottomSheet(
                height = btHeight,
            ) {
                scope.launch {
                    if (sheetState.isCollapsed) {
                        sheetState.expand()
                    }
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            DivideButton(
                height = btHeight,
            ) { onAction(CalculatorOperation.Divide) }

            ButtonNumber(
                height = btHeight,
                number = "8"
            ) { onAction(CalculatorNumber(8)) }

            ButtonNumber(
                height = btHeight,
                number = "5"
            ) { onAction(CalculatorNumber(5)) }

            ButtonNumber(
                height = btHeight,
                number = "2"
            ) { onAction(CalculatorNumber(2)) }

            ButtonNumber(
                height = btHeight,
                number = "0"
            ) { onAction(CalculatorNumber(0)) }
        }

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            ButtonMultiply(
                height = btHeight,
            ) { onAction(CalculatorOperation.Multiply) }

            ButtonNumber(
                height = btHeight,
                number = "9"
            ) { onAction(CalculatorNumber(9)) }

            ButtonNumber(
                height = btHeight,
                number = "6"
            ) { onAction(CalculatorNumber(6)) }

            ButtonNumber(
                height = btHeight,
                number = "3"
            ) { onAction(CalculatorNumber(3)) }

            CustomButton(
                height = btHeight,
                symbol = "."
            ) { onAction(CalculatorAction.Decimal) }
        }

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            ButtonBackspace(
                height = btHeight,
            ) { onAction(CalculatorAction.Backspace) }

            ButtonMinus(
                height = btHeight,
            ) { onAction(CalculatorOperation.Minus) }

            ButtonPlus(
                height = btHeight,
            ) { onAction(CalculatorOperation.Plus) }

            ButtonEqual(
                height = btHeight,
                keyboardHeight = keyboardHeightDp
            ) { onAction(CalculatorAction.Equal) }
        }
    }
}
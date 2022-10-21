package by.coolightman.calculator.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import by.coolightman.calculator.ui.theme.Blue700Border
import by.coolightman.calculator.util.CORNER_SHAPE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomKeyboard(
    scope: CoroutineScope,
    sheetState: BottomSheetState,
    modifier: Modifier = Modifier,
    onAction: (by.coolightman.calculator.model.CalculatorAction) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
                .clip(RoundedCornerShape(topStart = CORNER_SHAPE.dp, topEnd = CORNER_SHAPE.dp))
                .border(
                    width = 0.5.dp,
                    color = Blue700Border,
                    shape = RoundedCornerShape(topStart = CORNER_SHAPE.dp, topEnd = CORNER_SHAPE.dp)
                )

        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ButtonSqrt {
                    scope.launch { sheetState.collapse() }
                    onAction(by.coolightman.calculator.model.CalculatorAddOperation.Sqrt)
                }
                ButtonExponent {
                    scope.launch { sheetState.collapse() }
                    onAction(by.coolightman.calculator.model.CalculatorOperation.Exp)
                }
                ButtonFactorial {
                    scope.launch { sheetState.collapse() }
                    onAction(by.coolightman.calculator.model.CalculatorAddOperation.Factorial)
                }
                ButtonRoundToInt {
                    scope.launch { sheetState.collapse() }
                    onAction(by.coolightman.calculator.model.CalculatorAddOperation.Round)
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ButtonLg(symbol = "log2") {
                    scope.launch { sheetState.collapse() }
                    onAction(by.coolightman.calculator.model.CalculatorAddOperation.Log2)
                }
                ButtonLg(symbol = "lg") {
                    scope.launch { sheetState.collapse() }
                    onAction(by.coolightman.calculator.model.CalculatorAddOperation.Lg)
                }
                ButtonLongTitle(symbol = "1/x") {
                    scope.launch { sheetState.collapse() }
                    onAction(by.coolightman.calculator.model.CalculatorAddOperation.OneDivide)
                }
                ButtonLongTitle(symbol = "%") {
                    scope.launch { sheetState.collapse() }
                    onAction(by.coolightman.calculator.model.CalculatorAddOperation.Percent)
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ButtonLongTitle(symbol = "sin") {
                    scope.launch { sheetState.collapse() }
                    onAction(by.coolightman.calculator.model.CalculatorAddOperation.Sin)
                }
                ButtonLongTitle(symbol = "cos") {
                    scope.launch { sheetState.collapse() }
                    onAction(by.coolightman.calculator.model.CalculatorAddOperation.Cos)
                }
                ButtonLongTitle(symbol = "tg") {
                    scope.launch { sheetState.collapse() }
                    onAction(by.coolightman.calculator.model.CalculatorAddOperation.Tg)
                }
                ButtonLongTitle(symbol = "ctg") {
                    scope.launch { sheetState.collapse() }
                    onAction(by.coolightman.calculator.model.CalculatorAddOperation.Ctg)
                }
            }
        }
        DragElement(
            Modifier
                .padding(10.dp)
                .align(Alignment.TopCenter)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.surface)
                .align(Alignment.BottomCenter)
        )
    }
}
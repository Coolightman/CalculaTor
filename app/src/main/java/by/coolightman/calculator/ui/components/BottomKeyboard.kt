package by.coolightman.calculator.ui.components

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
import by.coolightman.calculator.model.CalculatorAction
import by.coolightman.calculator.model.CalculatorAddOperation
import by.coolightman.calculator.model.CalculatorOperation
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
    onAction: (CalculatorAction) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .clip(RoundedCornerShape(topStart = CORNER_SHAPE.dp, topEnd = CORNER_SHAPE.dp))
                .border(
                    width = 0.5.dp,
                    color = Blue700Border,
                    shape = RoundedCornerShape(topStart = CORNER_SHAPE.dp, topEnd = CORNER_SHAPE.dp)
                )

        ) {
            DragElement(Modifier.padding(top = 10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    ButtonSqrt {
                        scope.launch { sheetState.collapse() }
                        onAction(CalculatorAddOperation.Sqrt)
                    }

                    ButtonLg(symbol = "log2") {
                        scope.launch { sheetState.collapse() }
                        onAction(CalculatorAddOperation.Log2)
                    }

                    ButtonLongTitle(symbol = "sin") {
                        scope.launch { sheetState.collapse() }
                        onAction(CalculatorAddOperation.Sin)
                    }
                }

                Column {
                    ButtonExponent {
                        scope.launch { sheetState.collapse() }
                        onAction(CalculatorOperation.Exp)
                    }

                    ButtonLg(symbol = "lg") {
                        scope.launch { sheetState.collapse() }
                        onAction(CalculatorAddOperation.Lg)
                    }

                    ButtonLongTitle(symbol = "cos") {
                        scope.launch { sheetState.collapse() }
                        onAction(CalculatorAddOperation.Cos)
                    }
                }

                Column {
                    ButtonFactorial {
                        scope.launch { sheetState.collapse() }
                        onAction(CalculatorAddOperation.Factorial)
                    }

                    ButtonLongTitle(symbol = "1/x") {
                        scope.launch { sheetState.collapse() }
                        onAction(CalculatorAddOperation.OneDivide)
                    }

                    ButtonLongTitle(symbol = "tg") {
                        scope.launch { sheetState.collapse() }
                        onAction(CalculatorAddOperation.Tg)
                    }
                }

                Column {
                    ButtonRoundToInt {
                        scope.launch { sheetState.collapse() }
                        onAction(CalculatorAddOperation.Round)
                    }

                    ButtonLongTitle(symbol = "%") {
                        scope.launch { sheetState.collapse() }
                        onAction(CalculatorAddOperation.Percent)
                    }

                    ButtonLongTitle(symbol = "ctg") {
                        scope.launch { sheetState.collapse() }
                        onAction(CalculatorAddOperation.Ctg)
                    }
                }
            }

            Spacer(modifier = Modifier.height(1.dp))
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.surface)
                .align(Alignment.BottomCenter)
        )
    }
}
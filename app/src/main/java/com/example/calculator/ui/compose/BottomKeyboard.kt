package com.example.calculator.ui.compose

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
import com.example.calculator.model.CalculatorAction
import com.example.calculator.util.CORNER_SHAPE
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
            .height(100.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
                .clip(RoundedCornerShape(topStart = CORNER_SHAPE.dp, topEnd = CORNER_SHAPE.dp))
                .border(
                    width = 0.5.dp,
                    color = MaterialTheme.colors.primaryVariant,
                    shape = RoundedCornerShape(topStart = CORNER_SHAPE.dp, topEnd = CORNER_SHAPE.dp)
                )

        ) {
            DragElement(Modifier.padding(10.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CustomButton(symbol = ".") {
                    scope.launch { sheetState.collapse() }
                    onAction(CalculatorAction.Decimal)
                }
            }
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
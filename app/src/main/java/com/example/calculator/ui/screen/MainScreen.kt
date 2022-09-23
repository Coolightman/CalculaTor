package com.example.calculator.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.model.CalculatorAction
import com.example.calculator.model.MainScreenState
import com.example.calculator.ui.compose.*
import com.example.calculator.ui.theme.CalculaTorTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    state: MainScreenState,
    onAction: (CalculatorAction) -> Unit
) {
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetBackgroundColor = MaterialTheme.colors.surface,
        sheetShape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
        sheetPeekHeight = 0.dp,
        sheetElevation = 0.dp,
        sheetContent = {
            BottomKeyboard(scope = scope, sheetState = sheetState, onAction = onAction)
        }) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            MainRow(
                text = state.mainText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            SecondRaw(
                text = state.secondText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Keyboard(scope = scope, sheetState = sheetState, onAction = onAction)
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MainScreenPreview() {
    CalculaTorTheme {
        PrepareUI {
            MainScreen(
                state = MainScreenState(
                    mainText = "2+2x2",
                    secondText = "6"
                )
            ) {}
        }
    }
}
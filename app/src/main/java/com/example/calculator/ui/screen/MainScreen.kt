package com.example.calculator.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.model.CalculatorAction
import com.example.calculator.model.MainScreenState
import com.example.calculator.ui.compose.Keyboard
import com.example.calculator.ui.compose.MainRow
import com.example.calculator.ui.compose.PrepareUI
import com.example.calculator.ui.compose.SecondRaw
import com.example.calculator.ui.theme.CalculaTorTheme

@Composable
fun MainScreen(
    state: MainScreenState,
    onAction: (CalculatorAction) -> Unit
) {
    Scaffold { contentPadding ->
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
            Keyboard(onAction = onAction)
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
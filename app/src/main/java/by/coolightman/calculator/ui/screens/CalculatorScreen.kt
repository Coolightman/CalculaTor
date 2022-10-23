package by.coolightman.calculator.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import by.coolightman.calculator.ui.components.BottomKeyboard
import by.coolightman.calculator.ui.components.CalculatorMainPart
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CalculatorScreen(
    navHostController: NavHostController,
    viewModel: CalculatorViewModel
) {
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val scope = rememberCoroutineScope()

    var bottomKeyboardState by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        delay(100)
        bottomKeyboardState = true
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetBackgroundColor = MaterialTheme.colors.surface,
        sheetShape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
        sheetPeekHeight = 0.dp,
        sheetElevation = 0.dp,
        content = {
            CalculatorMainPart(
                viewModel = viewModel,
                scope = scope,
                sheetState = sheetState,
                navHostController = navHostController
            )
        },
        sheetContent = {
            if (bottomKeyboardState) {
                BottomKeyboard(
                    scope = scope,
                    sheetState = sheetState,
                    onAction = { viewModel.onAction(it) }
                )
            }
        }
    )
}
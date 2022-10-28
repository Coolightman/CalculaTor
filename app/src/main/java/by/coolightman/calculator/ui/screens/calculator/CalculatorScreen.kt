package by.coolightman.calculator.ui.screens.calculator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import by.coolightman.calculator.ui.components.BottomKeyboard
import by.coolightman.calculator.ui.components.CalculatorMainPart
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CalculatorScreen(
    navHostController: NavHostController,
    viewModel: CalculatorViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(
            initialValue = BottomSheetValue.Collapsed
        )
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
                uiState = uiState,
                scope = scope,
                sheetState = scaffoldState.bottomSheetState,
                navHostController = navHostController,
                onAction = { viewModel.onAction(it) },
                onClickTheme = { viewModel.saveThemePreference(it) }
            )
        },
        sheetContent = {
            BottomKeyboard(
                scope = scope,
                sheetState = scaffoldState.bottomSheetState,
                onAction = { viewModel.onAction(it) }
            )
        }
    )
}
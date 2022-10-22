package by.coolightman.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import by.coolightman.calculator.ui.models.ThemeMode
import by.coolightman.calculator.ui.screens.MainScreen
import by.coolightman.calculator.ui.theme.CalculaTorTheme
import by.coolightman.calculator.ui.screens.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<MainViewModel>()
            val uiState = viewModel.uiState
            val systemMode = isSystemInDarkTheme()

            var themeMode by remember {
                mutableStateOf(true)
            }
            LaunchedEffect(uiState.themeModePreference) {
                themeMode = when (uiState.themeModePreference) {
                    ThemeMode.SYSTEM_MODE -> systemMode
                    ThemeMode.DARK_MODE -> true
                    ThemeMode.LIGHT_MODE -> false
                }
            }
            CalculaTorTheme {
                MainScreen(uiState) {
                    viewModel.onAction(it)
                }
            }
        }
    }
}





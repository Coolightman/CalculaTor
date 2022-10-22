package by.coolightman.calculator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import by.coolightman.calculator.ui.navigation.AppNavigationHost
import by.coolightman.calculator.ui.theme.CalculaTorTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = viewModel<MainViewModel>()
            val uiState = viewModel.uiState
            val navHostController = rememberNavController()

            val themeMode by remember(uiState.themeModePreference) {
                mutableStateOf(uiState.themeModePreference.value)
            }

            CalculaTorTheme(darkTheme = themeMode) {
                AppNavigationHost(navHostController = navHostController)
            }
        }
    }
}





package by.coolightman.calculator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import by.coolightman.calculator.ui.components.PrepareUI
import by.coolightman.calculator.ui.navigation.AppNavigationHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = hiltViewModel<MainViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            val navHostController = rememberNavController()

            val themeMode by remember(uiState.themeModePreference) {
                mutableStateOf(uiState.themeModePreference.value)
            }

            PrepareUI(darkTheme = themeMode) {
                AppNavigationHost(navHostController = navHostController)
            }
        }
    }
}





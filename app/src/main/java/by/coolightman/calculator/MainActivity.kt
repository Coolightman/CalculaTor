package by.coolightman.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import by.coolightman.calculator.ui.compose.PrepareUI
import by.coolightman.calculator.ui.screen.MainScreen
import by.coolightman.calculator.ui.theme.CalculaTorTheme
import by.coolightman.calculator.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculaTorTheme {
                PrepareUI {
                    val viewModel = viewModel<MainViewModel>()
                    val state = viewModel.state
                    MainScreen(state) {
                        viewModel.onAction(it)
                    }
                }
            }
        }
    }
}





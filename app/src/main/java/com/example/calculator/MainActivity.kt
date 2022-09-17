package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.ui.compose.PrepareUI
import com.example.calculator.ui.screen.MainScreen
import com.example.calculator.ui.theme.CalculaTorTheme
import com.example.calculator.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculaTorTheme {
                PrepareUI {
                    val viewModel = viewModel<MainViewModel>()
                    val state = viewModel.mainScreenState
                    MainScreen(state) {
                        viewModel.onAction(it)
                    }
                }
            }
        }
    }
}





package by.coolightman.calculator.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import by.coolightman.calculator.ui.theme.CalculaTorTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun PrepareUI(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    CalculaTorTheme(darkTheme = darkTheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            DefineSystemBarsColors(darkMode = darkTheme)
            content()
        }
    }
}

@Composable
private fun DefineSystemBarsColors(darkMode: Boolean) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !darkMode
    val systemBarsColor = MaterialTheme.colors.background

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = systemBarsColor,
            darkIcons = useDarkIcons
        )
    }
}
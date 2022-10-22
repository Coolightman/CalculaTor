package by.coolightman.calculator.ui.screens

import by.coolightman.calculator.ui.models.ThemeMode

data class MainScreenUiState(
    val mainText: String = "",
    val secondText: String = "",
    val themeModePreference: ThemeMode = ThemeMode.DARK_MODE
)

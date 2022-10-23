package by.coolightman.calculator.ui.screens.history

import by.coolightman.calculator.model.HistoryRow

data class HistoryUiState(
    val list: List<HistoryRow> = emptyList()
)

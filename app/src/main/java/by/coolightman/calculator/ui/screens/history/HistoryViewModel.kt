package by.coolightman.calculator.ui.screens.history

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.coolightman.calculator.data.HistoryRowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRowRepository: HistoryRowRepository
) : ViewModel() {

    var uiState by mutableStateOf(HistoryUiState())
        private set

    init {
        getAllHistory()
    }

    private fun getAllHistory() {
        viewModelScope.launch {
            historyRowRepository.getAll().collectLatest {
                uiState = uiState.copy(list = it)
            }
        }
    }

    fun deleteHistory() {
        viewModelScope.launch {
            historyRowRepository.deleteAll()
        }
    }
}
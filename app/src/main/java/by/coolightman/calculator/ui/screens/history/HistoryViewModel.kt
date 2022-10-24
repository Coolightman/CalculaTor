package by.coolightman.calculator.ui.screens.history

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.coolightman.calculator.data.HistoryRowRepository
import by.coolightman.calculator.model.HistoryRow
import by.coolightman.calculator.util.HISTORY_MAX_SIZE
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
                uiState = uiState.copy(list = it.take(HISTORY_MAX_SIZE))
                cleanExcessHistory(it)
            }
        }
    }

    private suspend fun cleanExcessHistory(list: List<HistoryRow>) {
        if (list.size > HISTORY_MAX_SIZE){
            list.drop(HISTORY_MAX_SIZE).forEach {
                historyRowRepository.delete(it.id)
            }
        }
    }

    fun deleteHistory() {
        viewModelScope.launch {
            historyRowRepository.deleteAll()
        }
    }
}
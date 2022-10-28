package by.coolightman.calculator.ui

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.coolightman.calculator.ui.models.ThemeMode
import by.coolightman.calculator.util.THEME_MODE_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainActivityState())
    val uiState: StateFlow<MainActivityState> = _uiState.asStateFlow()

    init {
        getThemePreference()
    }

    private fun getThemePreference() {
        viewModelScope.launch {
            val dataStoreKey = booleanPreferencesKey(THEME_MODE_KEY)
            dataStore.data.map { preferences -> preferences[dataStoreKey] ?: true }
                .collectLatest { pref ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            themeModePreference = if (pref) ThemeMode.DARK_MODE
                            else ThemeMode.LIGHT_MODE
                        )
                    }
                }
        }
    }
}
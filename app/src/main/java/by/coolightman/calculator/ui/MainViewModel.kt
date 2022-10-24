package by.coolightman.calculator.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.coolightman.calculator.ui.models.ThemeMode
import by.coolightman.calculator.util.THEME_MODE_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    var uiState by mutableStateOf(MainActivityState())
        private set

    init {
        getThemePreference()
    }

    private fun getThemePreference() {
        viewModelScope.launch {
            val dataStoreKey = booleanPreferencesKey(THEME_MODE_KEY)
            dataStore.data.map { preferences -> preferences[dataStoreKey] ?: true }
                .collectLatest { pref ->
                    uiState = uiState.copy(
                        themeModePreference = if (pref) ThemeMode.DARK_MODE
                        else ThemeMode.LIGHT_MODE
                    )
                }
        }
    }
}
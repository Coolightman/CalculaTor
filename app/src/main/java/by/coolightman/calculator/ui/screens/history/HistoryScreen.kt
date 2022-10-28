package by.coolightman.calculator.ui.screens.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import by.coolightman.calculator.R
import by.coolightman.calculator.ui.components.EmptyContentSplash
import by.coolightman.calculator.ui.components.HistoryRowItem

@Composable
fun HistoryScreen(
    navHostController: NavHostController,
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val state = rememberLazyListState()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {},
            backgroundColor = MaterialTheme.colors.background,
            navigationIcon = {
                IconButton(onClick = { navHostController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back",
                        tint = MaterialTheme.colors.onSurface.copy(LocalContentAlpha.current)
                    )
                }
            },
            actions = {
                IconButton(onClick = { viewModel.deleteHistory() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete_history_24),
                        contentDescription = "delete history",
                        tint = MaterialTheme.colors.onSurface.copy(LocalContentAlpha.current)
                    )
                }
            }
        )
        if (uiState.list.isEmpty()) {
            EmptyContentSplash(
                iconId = R.drawable.ic_round_history_24,
                textId = R.string.no_history
            )
        } else {
            LazyColumn(
                state = state,
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(items = uiState.list, key = { it.id }) { row ->
                    HistoryRowItem(
                        expression = row.expression,
                        result = row.result
                    )
                }
            }
        }
    }
}
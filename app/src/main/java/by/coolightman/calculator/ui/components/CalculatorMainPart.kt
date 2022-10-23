package by.coolightman.calculator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import by.coolightman.calculator.R
import by.coolightman.calculator.ui.models.NavRoutes
import by.coolightman.calculator.ui.screens.calculator.CalculatorViewModel
import by.coolightman.calculator.ui.theme.CalculaTorTheme
import by.coolightman.calculator.ui.theme.InactiveBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CalculatorMainPart(
    viewModel: CalculatorViewModel,
    navHostController: NavHostController,
    scope: CoroutineScope,
    sheetState: BottomSheetState
) {
    val uiState = viewModel.uiState

    var isDropMenuExpanded by remember {
        mutableStateOf(false)
    }

    val themeMode by remember(uiState.themeModePreference) {
        mutableStateOf(uiState.themeModePreference.value)
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {},
            backgroundColor = MaterialTheme.colors.background,
            elevation = 0.dp,
            actions = {
                IconButton(onClick = { isDropMenuExpanded = true }) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "more"
                    )
                }

                DropdownMenu(
                    expanded = isDropMenuExpanded,
                    onDismissRequest = { isDropMenuExpanded = false }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(text = stringResource(R.string.dark_theme))
                        Spacer(modifier = Modifier.width(8.dp))
                        Switch(
                            checked = themeMode,
                            onCheckedChange = { viewModel.saveThemePreference(!themeMode) },
                            colors = SwitchDefaults.colors(
                                uncheckedThumbColor = InactiveBackground,
                                uncheckedTrackColor = InactiveBackground,
                                checkedThumbColor = MaterialTheme.colors.primaryVariant,
                                checkedTrackColor = MaterialTheme.colors.primaryVariant
                            )
                        )
                    }

                    Divider()

                    DropdownMenuItem(
                        onClick = {
                            isDropMenuExpanded = false
                            scope.launch { sheetState.collapse() }
                            navHostController.navigate(NavRoutes.History.route) {
                                launchSingleTop = true
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_round_history_24),
                            contentDescription = "history"
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = stringResource(R.string.history))
                    }
                }
            }
        )

        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {
            MainRow(
                text = uiState.mainText, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            SecondRaw(
                text = uiState.secondText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Keyboard(
                scope = scope,
                sheetState = sheetState,
                onAction = { viewModel.onAction(it) }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
private fun CalculatorMainPartPreview() {
    val viewModel = viewModel<CalculatorViewModel>()
    val scope = rememberCoroutineScope()
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val navController = rememberNavController()

    CalculaTorTheme {
        CalculatorMainPart(
            viewModel = viewModel,
            scope = scope,
            sheetState = sheetState,
            navHostController = navController
        )
    }
}
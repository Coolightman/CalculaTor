package by.coolightman.calculator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import by.coolightman.calculator.ui.models.NavRoutes
import by.coolightman.calculator.ui.screens.CalculatorScreen
import by.coolightman.calculator.ui.screens.CalculatorViewModel

@Composable
fun AppNavigationHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = NavRoutes.Main.route
    ) {

        composable(route = NavRoutes.Main.route) {
            val viewModel = viewModel<CalculatorViewModel>()
            CalculatorScreen(
                navHostController = navHostController,
                viewModel = viewModel
            )
        }
    }
}
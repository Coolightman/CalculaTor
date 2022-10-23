package by.coolightman.calculator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import by.coolightman.calculator.ui.models.NavRoutes
import by.coolightman.calculator.ui.screens.calculator.CalculatorScreen
import by.coolightman.calculator.ui.screens.history.HistoryScreen

@Composable
fun AppNavigationHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = NavRoutes.Main.route
    ) {

        composable(route = NavRoutes.Main.route) {
            CalculatorScreen(navHostController = navHostController)
        }

        composable(route = NavRoutes.History.route) {
            HistoryScreen(navHostController = navHostController)
        }
    }
}
package by.coolightman.calculator.ui.models

sealed class NavRoutes(val route: String) {

    object Main : NavRoutes("main")

    object History : NavRoutes("history")
}

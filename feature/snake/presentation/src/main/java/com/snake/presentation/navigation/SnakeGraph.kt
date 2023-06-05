package com.snake.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.snake.presentation.menu.MainMenuScreen
import com.snake.presentation.setting.SettingScreen

fun NavGraphBuilder.snakeGraph(navController: NavHostController) {
    navigation(
        route = Graph.SNAKE,
        startDestination = SnakeDestination.MainMenu.route,
    ) {
        composable(route = SnakeDestination.MainMenu.route) {
            MainMenuScreen(navController = navController)
        }

        composable(route = SnakeDestination.Settings.route) {
            SettingScreen()
        }
    }
}

object Graph {
    const val SNAKE = "snake_graph"
}

sealed class SnakeDestination(val route: String) {
    object MainMenu : SnakeDestination(route = "MAIN_MENU")

    object Settings : SnakeDestination(route = "SETTINGS")
}

package com.artemissoftware.quickgames.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.snake.presentation.navigation.snakeGraph

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = com.snake.presentation.navigation.Graph.SNAKE,
    ) {
        snakeGraph(navController = navController)
    }
}

object Graph {
    const val ROOT = "root_graph"
}

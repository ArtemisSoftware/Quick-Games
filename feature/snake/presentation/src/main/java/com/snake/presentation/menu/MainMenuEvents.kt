package com.snake.presentation.menu

sealed class MainMenuEvents {

    object GoToGame : MainMenuEvents()
    object GoToHighScore : MainMenuEvents()
    object GoToSettings : MainMenuEvents()
    object PopBackStack : MainMenuEvents()
}

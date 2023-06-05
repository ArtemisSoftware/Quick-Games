package com.snake.presentation.navigation

sealed class UiEvent {
    object PopBackStack : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    data class NavigatePopUpTo(val currentRoute: String, val destinationRoute: String) : UiEvent()
}

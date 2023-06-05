package com.snake.presentation.menu

import androidx.lifecycle.viewModelScope
import com.snake.presentation.composables.UiViewModel
import com.snake.presentation.navigation.SnakeDestination
import com.snake.presentation.navigation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor() : UiViewModel() {

    fun onTriggerEvent(event: MainMenuEvents) {
        when (event) {
            MainMenuEvents.GoToSettings -> {
                sendUiEvent(UiEvent.Navigate(SnakeDestination.Settings.route))
            }
            MainMenuEvents.GoToGame -> {
                sendUiEvent(UiEvent.Navigate(SnakeDestination.Game.route))
            }
            MainMenuEvents.GoToHighScore -> {
                sendUiEvent(UiEvent.Navigate(SnakeDestination.HighScore.route))
            }
            MainMenuEvents.PopBackStack -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}

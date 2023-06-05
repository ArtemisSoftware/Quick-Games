package com.snake.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.snake.presentation.navigation.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ManageUIEvents(
    uiEvent: Flow<UiEvent>,
    onNavigate: (UiEvent.Navigate) -> Unit = {},
    onNavigatePopUpTo: (UiEvent.NavigatePopUpTo) -> Unit = {},
    onPopBackStack: () -> Unit = {},
) {
    LaunchedEffect(key1 = Unit) {
        uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.PopBackStack -> { onPopBackStack.invoke() }
                is UiEvent.Navigate -> { onNavigate(event) }
                is UiEvent.NavigatePopUpTo -> { onNavigatePopUpTo(event) }
            }
        }
    }
}

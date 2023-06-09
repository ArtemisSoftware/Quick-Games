package com.snake.presentation.menu

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snake.presentation.R
import com.snake.presentation.composables.ManageUIEvents
import com.snake.presentation.composables.QGButton
import com.snake.presentation.composables.QGTopBar
import com.snake.presentation.ui.theme.SnakeTheme

@Composable
fun MainMenuScreen(
    viewModel: MainMenuViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    MainMenuScreenContent(
        event = viewModel::onTriggerEvent,
    )

    ManageUIEvents(
        uiEvent = viewModel.uiEvent,
        onNavigate = {
            navController.navigate(route = it.route)
        },
        onPopBackStack = {
            navController.popBackStack()
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainMenuScreenContent(
    event: (MainMenuEvents) -> Unit,
) {
    Scaffold(
        topBar = {
            QGTopBar(
                textId = R.string.main_menu,
                onBackClicked = {
                    event(MainMenuEvents.PopBackStack)
                },
            )
        },
        content = { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .padding(16.dp)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onBackground,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.snake),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.displayLarge,
                    textAlign = TextAlign.Start,
                )

                QGButton(
                    modifier = Modifier
                        .width(248.dp)
                        .padding(top = 64.dp),
                    textId = R.string.new_game,
                    onClick = {
                        event.invoke(MainMenuEvents.GoToGame)
                    },
                )

                QGButton(
                    modifier = Modifier
                        .width(248.dp),
                    textId = R.string.high_score,
                    onClick = {
                    },
                )

                QGButton(
                    modifier = Modifier
                        .width(248.dp),
                    textId = R.string.settings,
                    onClick = {
                        event.invoke(MainMenuEvents.GoToSettings)
                    },
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun MainMenuScreenContentPreview() {
    SnakeTheme {
        MainMenuScreenContent(event = {})
    }
}

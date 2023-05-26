package com.snake.presentation.setting

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snake.presentation.R
import com.snake.presentation.composables.QGButton
import com.snake.presentation.composables.QGTopBar
import com.snake.presentation.ui.theme.SnakeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(/*navController: NavHostController*/) {
//    val dataStore = GameCache(LocalContext.current)
    var text by remember { mutableStateOf(TextFieldValue("")) }
//    val scope = rememberCoroutineScope()
    val focusRequester = remember { FocusRequester() }
//    val context = LocalContext.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        topBar = {
            QGTopBar(
                textId = R.string.settings,
                onBackClicked = {},
            )
        },
        content = { contentPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = contentPadding.calculateTopPadding(),
                        bottom = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                    )
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onBackground,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.player_name),
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.displayLarge,
                    textAlign = TextAlign.Center,
                )

                TextField(
                    value = text,
                    onValueChange = { text = it },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        textColor = MaterialTheme.colorScheme.onBackground,
                        cursorColor = MaterialTheme.colorScheme.onBackground,
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .fillMaxWidth()
                        .padding(horizontal = 64.dp)
                        .border(width = 2.dp, color = MaterialTheme.colorScheme.onBackground),
                )

                QGButton(
                    modifier = Modifier
                        .width(248.dp)
                        .padding(16.dp),
                    textId = R.string.save,
                    onClick = {
//                        dataStore.savePlayerName(text.text.trim())
//                        Toast.makeText(context, R.string.player_name_updated, Toast.LENGTH_SHORT).show()
//                        navController.popBackStack()
                    },
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun SettingScreenPreview() {
    SnakeTheme {
        SettingScreen()
    }
}

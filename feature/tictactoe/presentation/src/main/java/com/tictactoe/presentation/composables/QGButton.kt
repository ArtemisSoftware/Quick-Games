package com.tictactoe.presentation.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tictactoe.presentation.R

@Composable
fun QGButton(
    @StringRes textId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        elevation = ButtonDefaults.buttonElevation(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Green,
            contentColor = Color.White,
        ),
    ) {
        Text(
            text = stringResource(id = textId),
            fontSize = 16.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun QGButtonPreview() {
    QGButton(textId = R.string.play_again, onClick = {})
}

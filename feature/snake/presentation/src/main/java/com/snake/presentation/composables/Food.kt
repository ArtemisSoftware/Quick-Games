package com.snake.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Food(
    modifier: Modifier,
) {
//    Box(
//        Modifier
//            .size(42.dp)
//            .background(
//                DarkGreen,
//                CircleShape,
//            ),
//    )

    Icon(
        modifier = modifier
            .background(
                DarkGreen,
                CircleShape,
            ),
        imageVector = Icons.Default.Fastfood,
        contentDescription = "Food Icon",
    )
}

@Preview(showBackground = true)
@Composable
private fun FoodPreview() {
    Food(
        modifier = Modifier
            .size(42.dp),
    )
}

package com.snake.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.FreeBreakfast
import androidx.compose.material.icons.filled.Highlight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snake.domain.models.Food
import com.snake.domain.models.FoodType

@Composable
fun SnakeFood(
    modifier: Modifier,
    foodType: FoodType

) {
    Icon(
        modifier = modifier
            .background(
                Color.Transparent,
            ),
        imageVector = getFoodVector(foodType),
        contentDescription = "Food Icon",
    )
}

private fun getFoodVector(foodType: FoodType): ImageVector {
    return when(foodType){
        FoodType.NEON -> Icons.Default.Highlight
        FoodType.HAMBURGER -> Icons.Default.Fastfood
        FoodType.FRUIT -> Icons.Default.FreeBreakfast
    }
}

@Preview(showBackground = true)
@Composable
private fun SnakeFoodPreview() {
    SnakeFood(
        modifier = Modifier
            .size(42.dp),
        foodType = FoodType.FRUIT
    )
}

package com.snake.presentation.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snake.domain.models.FoodType

@Composable
fun SnakeBody(
    modifier: Modifier = Modifier,
    foodType: FoodType,
) {

    when (foodType) {
        FoodType.NEON -> NeonBody(modifier = modifier)
        FoodType.HAMBURGER -> SnakeBody(modifier = modifier, color = listOf(Color.Green, Color.Yellow))
        FoodType.FRUIT -> SnakeBody(modifier = modifier, color = listOf(Color.Red, Color.Magenta))
    }
}

@Composable
private fun SnakeBody(
    modifier: Modifier = Modifier,
    color: List<Color>,
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.radialGradient(
                    colors = color,
                ),
                shape = RoundedCornerShape(4.dp),
            ),
    )
}

@Composable
private fun NeonBody(
    modifier: Modifier = Modifier,
) {
    val color = Color.Red

    val transparent = color
        .copy(alpha = 0f)
        .toArgb()

    Box(
        modifier = modifier
            .background(Color.Black, shape = RoundedCornerShape(4.dp)),
    ) {
        val paint = remember {
            Paint().apply {
                style = PaintingStyle.Stroke
                strokeWidth = 30f
            }
        }

        val frameworkPaint = remember {
            paint.asFrameworkPaint()
        }

        frameworkPaint.color = transparent

        frameworkPaint.setShadowLayer(
            10f,
            0f,
            0f,
            color
                .copy(alpha = .5f)
                .toArgb(),
        )

        Canvas(modifier = Modifier.fillMaxSize()) {
            inset(10.dp.toPx()) {
                this.drawIntoCanvas {
                    it.drawRoundRect(
                        left = 0f,
                        top = 0f,
                        right = size.width,
                        bottom = size.height,
                        radiusX = 5.dp.toPx(),
                        5.dp.toPx(),
                        paint = paint,
                    )

                    drawRoundRect(
                        Color.White,
                        cornerRadius = CornerRadius(5.dp.toPx(), 5.dp.toPx()),
                        style = Stroke(width = 2.dp.toPx()),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SnakeBodyPreview() {
    Column {
        SnakeBody(
            modifier = Modifier
                .size(42.dp),
            foodType = FoodType.FRUIT,
        )
        NeonBody()
    }
}

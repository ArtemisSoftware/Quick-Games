package com.snake.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.snake.presentation.R

private val SnakeFontFamily = FontFamily(
    fonts = listOf(Font(resId = R.font.snake)),
)

// Set of Material typography styles to start with
val SnakeTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = SnakeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    displayLarge = TextStyle(
        fontFamily = SnakeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = SnakeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
    ),
    labelMedium = Typography().labelMedium.copy(fontFamily = SnakeFontFamily)
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

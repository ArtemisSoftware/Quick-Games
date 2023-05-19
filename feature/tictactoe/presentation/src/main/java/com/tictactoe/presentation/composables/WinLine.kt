package com.tictactoe.presentation.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tictactoe.presentation.composables.models.LinePosition

@Composable
fun WinLine(
    linePosition: LinePosition,
    boardSize: Dp,
    color: Color = Color.Red,
) {
    Canvas(modifier = Modifier.size(boardSize)) {
        drawLine(
            color = color,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = getStart(linePosition = linePosition, size = size),
            end = getEnd(linePosition = linePosition, size = size),
        )
    }
}

private fun getStart(linePosition: LinePosition, size: Size): Offset {
    return when (linePosition) {
        LinePosition.HORIZONTAL_TOP -> Offset(x = 0f, y = size.height * 1 / 6)
        LinePosition.HORIZONTAL_MIDDLE -> Offset(x = 0f, y = size.height * 3 / 6)
        LinePosition.HORIZONTAL_BOTTOM -> Offset(x = 0f, y = size.height * 5 / 6)
        LinePosition.VERTICAL_LEFT -> Offset(x = size.width * 1 / 6, y = 0f)
        LinePosition.VERTICAL_MIDDLE -> Offset(x = size.width * 3 / 6, y = 0f)
        LinePosition.VERTICAL_RIGHT -> Offset(x = size.width * 5 / 6, y = 0f)
        LinePosition.DIAGONAL_RIGHT -> Offset(x = 0f, y = 0f)
        LinePosition.DIAGONAL_LEFT -> Offset(x = 0f, y = size.height)
    }
}

private fun getEnd(linePosition: LinePosition, size: Size): Offset {
    return when (linePosition) {
        LinePosition.HORIZONTAL_TOP -> Offset(x = size.width, y = size.height * 1 / 6)
        LinePosition.HORIZONTAL_MIDDLE -> Offset(x = size.width, y = size.height * 3 / 6)
        LinePosition.HORIZONTAL_BOTTOM -> Offset(x = size.width, y = size.height * 5 / 6)
        LinePosition.VERTICAL_LEFT -> Offset(x = size.width * 1 / 6, y = size.height)
        LinePosition.VERTICAL_MIDDLE -> Offset(x = size.width * 3 / 6, y = size.height)
        LinePosition.VERTICAL_RIGHT -> Offset(x = size.width * 5 / 6, y = size.height)
        LinePosition.DIAGONAL_RIGHT -> Offset(x = size.width, y = size.height)
        LinePosition.DIAGONAL_LEFT -> Offset(x = size.width, y = 0f)
    }
}

@Preview(showBackground = true)
@Composable
private fun WinLineHorizontalTopPreview() {
    WinLine(linePosition = LinePosition.HORIZONTAL_TOP, boardSize = 300.dp)
}

@Preview(showBackground = true)
@Composable
private fun WinLineHorizontalMiddlePreview() {
    WinLine(linePosition = LinePosition.HORIZONTAL_MIDDLE, boardSize = 300.dp)
}

@Preview(showBackground = true)
@Composable
private fun WinLineHorizontalBottomPreview() {
    WinLine(linePosition = LinePosition.HORIZONTAL_BOTTOM, boardSize = 300.dp)
}

@Preview(showBackground = true)
@Composable
private fun WinLineVerticalLeftPreview() {
    WinLine(linePosition = LinePosition.VERTICAL_LEFT, boardSize = 300.dp)
}

@Preview(showBackground = true)
@Composable
private fun WinLineVerticalMiddlePreview() {
    WinLine(linePosition = LinePosition.VERTICAL_MIDDLE, boardSize = 300.dp)
}

@Preview(showBackground = true)
@Composable
private fun WinLineVerticalRightPreview() {
    WinLine(linePosition = LinePosition.VERTICAL_RIGHT, boardSize = 300.dp)
}

@Preview(showBackground = true)
@Composable
private fun WinLineDiagonalRightPreview() {
    WinLine(linePosition = LinePosition.DIAGONAL_RIGHT, boardSize = 300.dp)
}

@Preview(showBackground = true)
@Composable
private fun WinLineDiagonalLeftPreview() {
    WinLine(linePosition = LinePosition.DIAGONAL_LEFT, boardSize = 300.dp)
}

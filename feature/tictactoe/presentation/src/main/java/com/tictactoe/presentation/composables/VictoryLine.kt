package com.tictactoe.presentation.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tictactoe.domain.models.VictoryType

fun DrawScope.victoryLine(
    color: Color = Color.Red,
    victoryType: VictoryType,
) {
    drawLine(
        color = color,
        strokeWidth = 10f,
        cap = StrokeCap.Round,
        start = getStart(victoryType = victoryType, size = size),
        end = getEnd(victoryType = victoryType, size = size),
    )
}

private fun getStart(victoryType: VictoryType, size: Size): Offset {
    return when (victoryType) {
        VictoryType.HORIZONTAL_TOP -> Offset(x = 0f, y = size.height * 1 / 6)
        VictoryType.HORIZONTAL_MIDDLE -> Offset(x = 0f, y = size.height * 3 / 6)
        VictoryType.HORIZONTAL_BOTTOM -> Offset(x = 0f, y = size.height * 5 / 6)
        VictoryType.VERTICAL_LEFT -> Offset(x = size.width * 1 / 6, y = 0f)
        VictoryType.VERTICAL_MIDDLE -> Offset(x = size.width * 3 / 6, y = 0f)
        VictoryType.VERTICAL_RIGHT -> Offset(x = size.width * 5 / 6, y = 0f)
        VictoryType.DIAGONAL_RIGHT -> Offset(x = 0f, y = 0f)
        VictoryType.DIAGONAL_LEFT -> Offset(x = 0f, y = size.height)
        VictoryType.DRAW -> Offset(x = 0f, y = 0f)
    }
}

private fun getEnd(victoryType: VictoryType, size: Size): Offset {
    return when (victoryType) {
        VictoryType.HORIZONTAL_TOP -> Offset(x = size.width, y = size.height * 1 / 6)
        VictoryType.HORIZONTAL_MIDDLE -> Offset(x = size.width, y = size.height * 3 / 6)
        VictoryType.HORIZONTAL_BOTTOM -> Offset(x = size.width, y = size.height * 5 / 6)
        VictoryType.VERTICAL_LEFT -> Offset(x = size.width * 1 / 6, y = size.height)
        VictoryType.VERTICAL_MIDDLE -> Offset(x = size.width * 3 / 6, y = size.height)
        VictoryType.VERTICAL_RIGHT -> Offset(x = size.width * 5 / 6, y = size.height)
        VictoryType.DIAGONAL_RIGHT -> Offset(x = size.width, y = size.height)
        VictoryType.DIAGONAL_LEFT -> Offset(x = size.width, y = 0f)
        VictoryType.DRAW -> Offset(x = 0f, y = 0f)
    }
}

@Preview(showBackground = true)
@Composable
private fun DrawVictoryLinePreview() {
    Canvas(
        modifier = Modifier.size(300.dp),
    ) {
        victoryLine(victoryType = VictoryType.DIAGONAL_LEFT)
    }
}

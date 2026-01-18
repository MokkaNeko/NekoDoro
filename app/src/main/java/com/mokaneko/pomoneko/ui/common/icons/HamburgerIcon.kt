package com.mokaneko.pomoneko.ui.common.icons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun HamburgerIcon(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    strokeWidth: Float = 2f
) {
    Canvas(
        modifier = modifier
            .size(width = 20.dp, height = 10.dp)
    ) {
        val strokePx = strokeWidth.dp.toPx()

        val startX = strokePx / 2
        val endX = size.width - strokePx / 2

        drawLine(
            color = color,
            start = Offset(startX, strokePx / 2),
            end = Offset(endX, strokePx / 2),
            strokeWidth = strokePx,
            cap = StrokeCap.Round
        )

        drawLine(
            color = color,
            start = Offset(startX, size.height / 2),
            end = Offset(endX, size.height / 2),
            strokeWidth = strokePx,
            cap = StrokeCap.Round
        )

        drawLine(
            color = color,
            start = Offset(startX, size.height - strokePx / 2),
            end = Offset(endX, size.height - strokePx / 2),
            strokeWidth = strokePx,
            cap = StrokeCap.Round
        )
    }
}
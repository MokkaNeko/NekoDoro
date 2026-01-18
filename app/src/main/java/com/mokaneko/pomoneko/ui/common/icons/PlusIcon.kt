package com.mokaneko.pomoneko.ui.common.icons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.mokaneko.pomoneko.ui.theme.White

@Composable
fun PlusIcon(
    modifier: Modifier = Modifier,
    strokeWidth: Float = 8f,
    color: Color = White
) {
    Canvas(modifier = modifier) {
        val centerX = size.width / 2
        val centerY = size.height / 2
        drawLine(
            color = color,
            start = Offset(0f, centerY),
            end = Offset(size.width, centerY),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
        drawLine(
            color = color,
            start = Offset(centerX, 0f),
            end = Offset(centerX, size.height),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
    }
}
package com.mokaneko.pomoneko.ui.common.icons

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import com.mokaneko.pomoneko.ui.theme.White

@Composable
fun MinusIcon(
    modifier: Modifier = Modifier,
    strokeWidth: Float = 8f,
    color: Color = White
) {
    Canvas(modifier = modifier) {
        val centerLeft = Offset(0f, size.height/2)
        val centerRight = Offset(size.width, size.height/2)
        drawLine(
            color = color,
            start = centerLeft,
            end = centerRight,
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
    }
}
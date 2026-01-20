package com.mokaneko.pomoneko.ui.timer.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mokaneko.pomoneko.ui.theme.Inactive
import com.mokaneko.pomoneko.ui.theme.White

@Composable
fun TimerCircleBorder(
    progress: Float,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 8.dp,
    color1: Color = White,
    color2: Color = Inactive
) {
    Canvas(
        modifier = modifier.aspectRatio(1f)
    ) {
        val stroke = Stroke(
            width = strokeWidth.toPx()
        )
        val startAngle = 90f
        val sweepAngle = 360f * progress
        drawCircle(
            color = color2,
            radius = (size.minDimension / 2),
            style = stroke
        )
        drawArc(
            color = color1,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = stroke
        )
    }
}
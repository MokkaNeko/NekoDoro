package com.mokaneko.pomoneko.ui.common.icons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.mokaneko.pomoneko.ui.theme.White

@Composable
fun ResetIcon(
    modifier: Modifier = Modifier,
    color: Color = White
) {
    Canvas(modifier = modifier.size(20.dp)) {
        val strokeWidth = size.width * 0.12f
        drawArc(
            color = color,
            startAngle = 30f,
            sweepAngle = -260f,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
        val path = Path().apply {
            moveTo(size.width * 0.8f, size.height * 0.65f)
            lineTo(size.width * 0.75f, size.height * 0.95f)
            lineTo(size.width * 1.05f, size.height * 0.85f)
            close()
        }
        drawPath(path, color = color)
    }
}
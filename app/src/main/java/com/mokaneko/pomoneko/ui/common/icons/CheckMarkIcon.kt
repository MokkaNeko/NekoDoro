package com.mokaneko.pomoneko.ui.common.icons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CheckMarkIcon(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    strokeWidth: Float = 10f
) {
    Canvas(
        modifier = modifier
            .size(12.dp)
    ) {
        drawPath(
            path = Path().apply {
                moveTo(size.width, 0f)
                lineTo(size.width/2, size.height)
                lineTo(0f, size.height/2f)
            },
            color = color,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}

@Composable
@Preview
fun CheckMarkIconPreview() {
    CheckMarkIcon()
}
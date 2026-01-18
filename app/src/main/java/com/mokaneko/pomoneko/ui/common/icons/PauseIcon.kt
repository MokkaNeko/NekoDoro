package com.mokaneko.pomoneko.ui.common.icons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mokaneko.pomoneko.ui.theme.White

@Composable
fun PauseIcon(
    modifier: Modifier = Modifier,
    color: Color = White,
) {
    Canvas(
        modifier = modifier.size(25.dp)
    ) {
        val barWidth = size.width * 0.25f
        val gap = size.width * 0.15f
        val totalWidth = barWidth*2 + gap
        val startX = (size.width - totalWidth) / 2

        drawRect(
            color = color,
            topLeft = Offset(startX, 0f),
            size = Size(barWidth, size.height)
        )
        drawRect(
            color = color,
            topLeft = Offset(startX + barWidth + gap, 0f),
            size = Size(barWidth, size.height)
        )
    }
}
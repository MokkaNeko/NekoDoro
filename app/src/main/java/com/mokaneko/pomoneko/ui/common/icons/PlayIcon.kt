package com.mokaneko.pomoneko.ui.common.icons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import com.mokaneko.pomoneko.ui.theme.White

@Composable
fun PlayIcon(
    modifier: Modifier = Modifier,
    color: Color = White
) {
    Canvas(modifier = modifier.size(25.dp)) {
        val trianglePath = Path().apply {
            moveTo(size.width, size.height / 2f)
            lineTo(0f, 0f)
            lineTo(0f, size.height)
            close()
        }
        drawPath(
            path = trianglePath,
            color = color
        )
    }
}
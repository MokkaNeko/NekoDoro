package com.mokaneko.pomoneko.ui.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mokaneko.pomoneko.ui.theme.Lime
import com.mokaneko.pomoneko.ui.theme.SemiTransparent
import com.mokaneko.pomoneko.ui.theme.White
import com.mokaneko.pomoneko.ui.theme.itim

@Composable
fun SettingsSessionCounts(
    modifier: Modifier = Modifier,
    sessionCount: Int,
    onSessionChange: (Int) -> Unit,
    minSession: Int = 1,
    maxSession: Int = 8
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(SemiTransparent, shape = RoundedCornerShape(10))
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = sessionCount.toString(),
                color = White,
                fontFamily = itim,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Slider(
                value = sessionCount.toFloat(),
                onValueChange = { value ->
                    onSessionChange(value.toInt())
                },
                valueRange = minSession.toFloat()..maxSession.toFloat(),
                steps = maxSession - minSession - 1,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxHeight(0.8f)
                ,
                colors = SliderDefaults.colors(White, Lime, activeTickColor = White, inactiveTickColor = White)
            )
        }
    }
}
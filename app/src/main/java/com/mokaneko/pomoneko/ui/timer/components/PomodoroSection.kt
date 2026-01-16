package com.mokaneko.pomoneko.ui.timer.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mokaneko.pomoneko.data.local.PomodoroPhase
import com.mokaneko.pomoneko.ui.theme.Inactive
import com.mokaneko.pomoneko.ui.theme.Pink
import com.mokaneko.pomoneko.ui.theme.White

@Composable
fun PomodoroSection(
    totalSection: Int,
    currentSection: Int,
    phase: PomodoroPhase,
    circleSize: Dp = 36.dp
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalSection) { index ->
            val sectionNumber = index + 1

            val color = when {
                sectionNumber < currentSection -> White // completed
                sectionNumber == currentSection -> {
                    when (phase) {
                        PomodoroPhase.FOCUS -> White
                        PomodoroPhase.SHORT_BREAK,
                        PomodoroPhase.LONG_BREAK -> Pink
                    }
                }
                else -> Inactive // not started
            }
            Canvas(
                modifier = Modifier
                    .size(circleSize)
                    .padding(horizontal = 6.dp)
            ) {
                drawCircle(color = color)
            }
        }
    }
}
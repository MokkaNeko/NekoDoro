package com.mokaneko.pomoneko.ui.timer.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mokaneko.pomoneko.data.local.PomodoroPhase
import com.mokaneko.pomoneko.ui.theme.Inactive
import com.mokaneko.pomoneko.ui.theme.White

@Composable
fun PomodoroSection(
    totalSection: Int,
    currentSection: Int,
    phase: PomodoroPhase,
    circleSize: Dp = 30.dp
) {
    if (totalSection < 6) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SectionRow(
                startIndex = 0,
                count = totalSection,
                currentSection = currentSection,
                phase = phase,
                circleSize = circleSize
            )
        }
    } else {
        val topCount = (totalSection + 1) / 2
        val bottomCount = totalSection / 2

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SectionRow(
                startIndex = 0,
                count = topCount,
                currentSection = currentSection,
                phase = phase,
                circleSize = circleSize
            )

            Spacer(modifier = Modifier.height(8.dp))

            SectionRow(
                startIndex = topCount,
                count = bottomCount,
                currentSection = currentSection,
                phase = phase,
                circleSize = circleSize
            )
        }
    }
}


@Composable
private fun SectionRow(
    startIndex: Int,
    count: Int,
    currentSection: Int,
    phase: PomodoroPhase,
    circleSize: Dp
) {
    val infiniteTransition = rememberInfiniteTransition(label = "BlinkTransition")

    val blinkAlpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "BlinkAlpha"
    )

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(count) { i ->
            val sectionNumber = startIndex + i + 1
            val isBreak = phase != PomodoroPhase.FOCUS
            val isLongBreak = phase == PomodoroPhase.LONG_BREAK

            val shouldBlink = when {
                isBreak && isLongBreak -> true
                isBreak && sectionNumber == currentSection + 1 -> true
                else -> false
            }

            val color = if (sectionNumber <= currentSection || shouldBlink) White else Inactive
            val alpha = if (shouldBlink) blinkAlpha else 1f

            Canvas(
                modifier = Modifier
                    .size(circleSize)
                    .padding(horizontal = 6.dp)
            ) {
                drawCircle(color = color, alpha = alpha)
            }
        }
    }
}

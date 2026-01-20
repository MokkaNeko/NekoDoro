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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
    circleSize: Dp = 36.dp
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val infiniteTransition = rememberInfiniteTransition(label = "BlinkTransition")

        val blinkAlpha by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 0.3f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 500, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "BlinkAlpha"
        )

        repeat(totalSection) { index ->
            val sectionNumber = index + 1
            val isBreak = phase != PomodoroPhase.FOCUS
            val isLongBreak = phase == PomodoroPhase.LONG_BREAK

            val shouldBlink = when {
                isBreak && isLongBreak -> true
                isBreak && sectionNumber == currentSection + 1 -> true

                else -> false
            }

            val baseColor = if (sectionNumber <= currentSection || shouldBlink) White else Inactive
            val finalAlpha = if (shouldBlink) blinkAlpha else 1f
            Canvas(
                modifier = Modifier
                    .size(circleSize)
                    .padding(horizontal = 6.dp)
            ) {
                drawCircle(
                    color = baseColor,
                    alpha = finalAlpha
                    )
            }
        }
    }
}
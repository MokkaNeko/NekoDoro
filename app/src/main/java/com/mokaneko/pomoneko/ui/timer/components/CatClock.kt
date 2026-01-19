package com.mokaneko.pomoneko.ui.timer.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mokaneko.pomoneko.R
import com.mokaneko.pomoneko.data.local.PomodoroPhase
import com.mokaneko.pomoneko.ui.theme.Inactive
import com.mokaneko.pomoneko.ui.theme.White
import com.mokaneko.pomoneko.ui.timer.state.TimerState

@Composable
fun CatClock(
    progress: Float,
    phase: PomodoroPhase,
    timerState: TimerState
) {
    val animatedProgress = remember { Animatable(progress) }

    var lastPhase by remember { mutableStateOf(phase) }
    var latchedVisualProgress by remember { mutableStateOf(0f) }

    LaunchedEffect(progress, timerState) {
        if (timerState == TimerState.STOPPED) {
            animatedProgress.animateTo(
                targetValue = 0f,
                animationSpec = tween(600, easing = LinearEasing)
            )
        } else {
            animatedProgress.animateTo(
                targetValue = progress,
                animationSpec = tween(1000, easing = LinearEasing)
            )
        }
    }

    val rawVisualProgress = when (phase) {
        PomodoroPhase.FOCUS -> animatedProgress.value
        else -> 1f - animatedProgress.value
    }

    LaunchedEffect(phase, rawVisualProgress) {
        latchedVisualProgress = rawVisualProgress
        lastPhase = phase
    }

    val visualProgress =
        if (phase != lastPhase) {
            latchedVisualProgress
        } else {
            rawVisualProgress
        }

    val targetColor = when {
        timerState == TimerState.STOPPED -> Inactive
        phase != PomodoroPhase.FOCUS -> Inactive
        else -> White
    }
    val animatedColor by animateColorAsState(
        targetValue = targetColor,
        animationSpec = tween(
            durationMillis = 600,
            easing = LinearOutSlowInEasing
        ),
        label = "CatColor"
    )

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        val clockSize = maxWidth * 0.75f
        val faceSize = clockSize * 0.86f
        val earWidth = clockSize * 1.5f
        val earOffsetY = -(clockSize * 0.42f)
        Box(contentAlignment = Alignment.Center) {
            TimerCircleBorder(
                progress = visualProgress,
                modifier = Modifier.size(clockSize),
                strokeWidth = 18.dp
            )

            Image(
                painter = painterResource(R.drawable.ic_cat_face),
                contentDescription = null,
                modifier = Modifier.size(faceSize),
                colorFilter = ColorFilter.tint(animatedColor)
            )

            Image(
                painter = painterResource(R.drawable.ic_cat_ear),
                contentDescription = null,
                modifier = Modifier
                    .size(earWidth)
                    .offset(y = earOffsetY),
                colorFilter = ColorFilter.tint(animatedColor)
            )
        }
    }
}
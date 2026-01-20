package com.mokaneko.pomoneko.ui.timer.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mokaneko.pomoneko.R
import com.mokaneko.pomoneko.data.local.PomodoroPhase
import com.mokaneko.pomoneko.ui.theme.Inactive
import com.mokaneko.pomoneko.ui.theme.SemiTransparent
import com.mokaneko.pomoneko.ui.theme.White
import com.mokaneko.pomoneko.ui.timer.state.TimerState

@Composable
fun CatClock(
    progress: Float,
    phase: PomodoroPhase,
    timerState: TimerState
) {
    val animatedProgress by animateFloatAsState(
        targetValue = if (timerState == TimerState.STOPPED) 0f else progress,
        animationSpec = if (timerState == TimerState.STOPPED) {
            tween(1000)
        } else {
            tween(durationMillis = 1000, easing = LinearEasing)
        },
        label = "TimerProgress"
    )
    val activeColor = when (phase) {
        PomodoroPhase.FOCUS -> White
        PomodoroPhase.SHORT_BREAK, PomodoroPhase.LONG_BREAK -> Inactive
    }
    val animatedActiveColor by animateColorAsState(
        targetValue = if (timerState == TimerState.STOPPED) Inactive else activeColor,
        animationSpec = tween(1000),
        label = "PhaseColor"
    )
    val inactiveColor = when (phase) {
        PomodoroPhase.FOCUS -> Inactive
        PomodoroPhase.SHORT_BREAK, PomodoroPhase.LONG_BREAK -> SemiTransparent
    }
    val animatedInactiveColor by animateColorAsState(
        targetValue = if (timerState == TimerState.STOPPED) Inactive else inactiveColor,
        animationSpec = tween(1000),
        label = "PhaseColor"
    )

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
                progress = animatedProgress,
                modifier = Modifier.size(clockSize),
                strokeWidth = 18.dp,
                color1 = animatedActiveColor,
                color2 = animatedInactiveColor
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
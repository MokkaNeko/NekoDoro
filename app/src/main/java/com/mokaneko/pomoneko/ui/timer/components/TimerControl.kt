package com.mokaneko.pomoneko.ui.timer.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import com.mokaneko.pomoneko.ui.common.icons.PlayIcon
import com.mokaneko.pomoneko.ui.common.icons.PauseIcon
import com.mokaneko.pomoneko.ui.common.icons.ResetIcon
import com.mokaneko.pomoneko.ui.timer.state.TimerState

@Composable
fun TimerControl(
    timerState: TimerState,
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onReset: () -> Unit
) {
    Box(
        modifier = Modifier
            .layoutId("timerControl")
            .fillMaxWidth()
            .height(64.dp)
    ) {
        when (timerState) {
            TimerState.STOPPED -> {
                Button(
                    onClick = onPlay,
                    modifier = Modifier
                        .align(Alignment.Center),
                    shape = RoundedCornerShape(10),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Transparent
                    )
                ) { PlayIcon() }
            }
            TimerState.PAUSED -> {
                Button(
                    onClick = onPlay,
                    modifier = Modifier
                        .align(Alignment.Center),
                    shape = RoundedCornerShape(10),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Transparent
                    )
                ) { PlayIcon() }
                Button(
                    onClick = onReset,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 60.dp),
                    shape = RoundedCornerShape(10),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Transparent
                    )
                ) { ResetIcon() }
            }
            else -> {
                Button(
                    onClick = onPause,
                    modifier = Modifier
                        .align(Alignment.Center),
                    shape = RoundedCornerShape(10),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Transparent
                    )
                ) { PauseIcon() }
            }
        }
    }
}
package com.mokaneko.pomoneko.ui.timer

import android.app.Activity
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mokaneko.pomoneko.data.local.PomodoroPhase
import com.mokaneko.pomoneko.domain.event.TimerEvent
import com.mokaneko.pomoneko.ui.common.icons.HamburgerIcon
import com.mokaneko.pomoneko.ui.theme.Green
import com.mokaneko.pomoneko.ui.theme.White
import com.mokaneko.pomoneko.ui.theme.itim
import com.mokaneko.pomoneko.ui.timer.components.CatClock
import com.mokaneko.pomoneko.ui.timer.components.PomodoroSection
import com.mokaneko.pomoneko.ui.timer.components.TaskName
import com.mokaneko.pomoneko.ui.timer.components.TimerControl
import com.mokaneko.pomoneko.ui.timer.state.TimerUiState
import com.mokaneko.pomoneko.util.VibrationHelper

@Composable
fun TimerScreen(
    viewModel: TimerViewModel = hiltViewModel(),
    onOpenSettings: () -> Unit,
){
    val uiState by viewModel.uiState
    val context = LocalContext.current
    val vibrator = remember { VibrationHelper(context) }
    val activity = context as Activity
    val stayAwake by viewModel.stayAwake.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.event.collect {event ->
            when (event) {
                is TimerEvent.PhaseChanged -> {
                    vibrator.vibrateShort()
                }
            }
        }
    }

    DisposableEffect(stayAwake) {
        if (stayAwake) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        } else {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
        onDispose {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    TimerContent(
        uiState = uiState,
        onNameChange = viewModel::updateTaskName,
        onPlay = viewModel::onPlay,
        onPause = viewModel::onPause,
        onReset = viewModel::onReset,
        onOpenSettings = onOpenSettings
    )
}

@Composable
fun TimerContent(
    uiState: TimerUiState,
    onNameChange: (String) -> Unit,
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onReset: () -> Unit,
    onOpenSettings: () -> Unit
){
    Box(
        modifier = Modifier
            .background(Green)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onOpenSettings,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .height(20.dp)
                        .width(24.dp),
                    shape = RoundedCornerShape(10),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Transparent
                    ),
                    contentPadding = PaddingValues(2.dp)
                ) {
                    HamburgerIcon(
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                TaskName(
                    name = uiState.taskName,
                    onNameChange = onNameChange
                )
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(8f),
                contentAlignment = Alignment.Center
            ){
                CatClock(
                    progress = uiState.progress,
                    phase = uiState.phase,
                    timerState = uiState.timerState
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PomodoroSection(
                    totalSection = uiState.totalSection,
                    currentSection = uiState.currentSection,
                    phase = uiState.phase
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = uiState.timerText,
                    color = White,
                    fontFamily = itim,
                    fontSize = 28.sp,
                    textAlign = Center
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TimerControl(
                    timerState = uiState.timerState,
                    onPlay = onPlay,
                    onPause = onPause,
                    onReset = onReset
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {
                val section: String = when(uiState.phase) {
                    PomodoroPhase.FOCUS -> "Focus"
                    PomodoroPhase.SHORT_BREAK -> "Short Break"
                    PomodoroPhase.LONG_BREAK -> "Long Break"
                }
                Text(
                    modifier = Modifier
                        .layoutId("sectionText"),
                    text = section,
                    color = White,
                    fontFamily = itim,
                    fontSize = 24.sp,
                    textAlign = Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimerScreenPreview() {
    TimerScreen(
        onOpenSettings = {}
    )
}

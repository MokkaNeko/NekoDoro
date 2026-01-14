package com.mokaneko.pomoneko.ui.timer

import com.mokaneko.pomoneko.data.local.PomodoroPhase

data class TimerUiState(
    val taskName: String,
    val timerText: String,
    val currentSection: Int,
    val totalSection: Int,
    val phase: PomodoroPhase,
    val timerState: TimerState,
    val progress: Float
)

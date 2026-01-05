package com.mokaneko.pomoneko.ui.timer

data class TimerUiState(
    val taskName: String,
    val timerText: String,
    val section: Int,
    val sectionText: String,
    val timerState: TimerState,
    val progress: Float
)

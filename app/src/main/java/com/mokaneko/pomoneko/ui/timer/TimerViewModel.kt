package com.mokaneko.pomoneko.ui.timer

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {

    private val _uiState = mutableStateOf(
        TimerUiState(
            taskName = "This cat needs a name",
            timerText = "25:00",
            section = 4,
            sectionText = "Focus",
            timerState = TimerState.STOPPED
        )
    )
    val uiState: State<TimerUiState> = _uiState

    fun onPlay() {
        _uiState.value = _uiState.value.copy(timerState = TimerState.RUNNING)
    }
    fun onPause() {
        _uiState.value = _uiState.value.copy(timerState = TimerState.PAUSED)
    }
    fun onReset() {
        _uiState.value = _uiState.value.copy(
            timerState = TimerState.STOPPED,
            timerText = "25:00"
        )
    }
}
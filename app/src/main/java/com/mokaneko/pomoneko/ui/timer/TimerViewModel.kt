package com.mokaneko.pomoneko.ui.timer

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {

    private val _uiState = mutableStateOf(
        TimerUiState(
            taskName = "This cat needs a name",
            timerText = "1:00",
            section = 4,
            sectionText = "Focus",
            timerState = TimerState.STOPPED,
            progress = 1f
        )
    )
    val uiState: State<TimerUiState> = _uiState

    private var timerJob: Job? = null
    private val totalSeconds = 1*60
    private var remainingSeconds = totalSeconds

    private fun formatTime(seconds: Int): String {
        val m = seconds / 60
        val s = seconds % 60
        return "%02d:%02d".format(m, s)
    }

    fun onPlay() {
        if (timerJob != null) return

        _uiState.value = uiState.value.copy(
            timerState = TimerState.RUNNING
        )

        timerJob = viewModelScope.launch {
            while (remainingSeconds > 0) {
                delay(1000)
                remainingSeconds--
                _uiState.value = uiState.value.copy(
                    timerText = formatTime(remainingSeconds),
                    progress = remainingSeconds.toFloat() / totalSeconds
                )
            }
            onReset()
        }
    }
    fun onPause() {
        timerJob?.cancel()
        timerJob = null
        _uiState.value = _uiState.value.copy(timerState = TimerState.PAUSED)
    }
    fun onReset() {
        timerJob?.cancel()
        timerJob = null
        remainingSeconds = 1*60
        _uiState.value = _uiState.value.copy(
            timerState = TimerState.STOPPED,
            timerText = formatTime(remainingSeconds),
            progress = 1f
        )
    }
}
package com.mokaneko.pomoneko.ui.timer

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mokaneko.pomoneko.data.local.PomodoroPhase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {

    /* ------------ State ------------ */
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

    /* ------------ Variables ------------- */
    private var timerJob: Job? = null
    private val focusDuration = 1 * 60
    private val shortBreakDuration = 30
    private val longBreakDuration = 1 * 60
    private val totalSection = 4

    private var currentPhase = PomodoroPhase.FOCUS
    private var currentSection = 1
    private var totalSeconds = focusDuration
    private var remainingSeconds = totalSeconds

    /* ----------- Helpers ------------- */
    private fun formatTime(seconds: Int): String {
        val m = seconds / 60
        val s = seconds % 60
        return "%02d:%02d".format(m, s)
    }


    private fun updateUiState() {
        _uiState.value = _uiState.value.copy(
            timerText = formatTime(remainingSeconds),
            progress = remainingSeconds.toFloat() / totalSeconds,
            section = currentSection,
            sectionText = when (currentPhase) {
                PomodoroPhase.FOCUS -> "Focus"
                PomodoroPhase.SHORT_BREAK -> "Short Break"
                PomodoroPhase.LONG_BREAK -> "Long Break"
            }
        )
    }

    /* ------------ functions ------------ */

    private fun moveToNextPhase() {
        when (currentPhase) {
            PomodoroPhase.FOCUS -> {
                if (currentSection == totalSection) {
                    currentPhase = PomodoroPhase.LONG_BREAK
                    totalSeconds = longBreakDuration
                } else {
                    currentPhase = PomodoroPhase.SHORT_BREAK
                    totalSeconds = shortBreakDuration
                }
            }
            PomodoroPhase.SHORT_BREAK -> {
                currentSection++
                currentPhase = PomodoroPhase.FOCUS
                totalSeconds = focusDuration
            }
            PomodoroPhase.LONG_BREAK -> {
                currentSection = 1
                currentPhase = PomodoroPhase.FOCUS
                totalSeconds = focusDuration
            }
        }
        remainingSeconds = totalSeconds
        updateUiState()
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
                updateUiState()
            }
            moveToNextPhase()
            timerJob = null
            onPlay()
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

        currentPhase = PomodoroPhase.FOCUS
        currentSection = 1
        totalSeconds = focusDuration
        remainingSeconds = totalSeconds

        _uiState.value = _uiState.value.copy(
            timerState = TimerState.STOPPED
        )

        updateUiState()
    }
}
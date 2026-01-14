package com.mokaneko.pomoneko.ui.timer

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mokaneko.pomoneko.data.local.PomodoroPhase
import com.mokaneko.pomoneko.data.local.PomodoroSettingEntity
import com.mokaneko.pomoneko.data.repository.PomodoroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(private val repository: PomodoroRepository) : ViewModel() {

    /* ------------ State ------------ */
    private val _uiState = mutableStateOf(
        TimerUiState(
            taskName = "This cat needs a name",
            timerText = "25:00",
            currentSection = 1,
            totalSection = 4,
            phase = PomodoroPhase.FOCUS,
            timerState = TimerState.STOPPED,
            progress = 0f
        )
    )
    val uiState: State<TimerUiState> = _uiState

    /* ------------ Variables ------------- */
    private var timerJob: Job? = null
    private var focusDuration = 0
    private var shortBreakDuration = 0
    private var longBreakDuration = 0
    private var totalSection = 0


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
            progress = if (totalSeconds > 0) remainingSeconds.toFloat() / totalSeconds else 0f,
            currentSection = currentSection,
            totalSection = totalSection,
            phase = currentPhase
        )
    }

    private fun applySetting(setting: PomodoroSettingEntity) {
        focusDuration = setting.focusDuration
        shortBreakDuration = setting.shortBreakDuration
        longBreakDuration = setting.longBreakDuration
        totalSection = setting.totalSection

        currentPhase = PomodoroPhase.FOCUS
        currentSection = 1
        totalSeconds = focusDuration
        remainingSeconds = totalSeconds

        _uiState.value = _uiState.value.copy(
            taskName = setting.taskName
        )

        updateUiState()
    }
    private suspend fun saveDefaultSetting() {
        repository.save(
            PomodoroSettingEntity(
                taskName = "This cat needs a name",
                focusDuration = 25*60,
                shortBreakDuration = 5*60,
                longBreakDuration = 15*60,
                totalSection = 4
            )
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

    fun updateSetting(
        taskName: String,
        focus: Int,
        short: Int,
        long: Int,
        section: Int
    ) {
        viewModelScope.launch {
            repository.save(
                PomodoroSettingEntity(
                    taskName = taskName,
                    focusDuration = focus * 60,
                    shortBreakDuration = short * 60,
                    longBreakDuration = long * 60,
                    totalSection = section
                )
            )
        }
    }

    fun updateTaskName(name: String) {
        _uiState.value = _uiState.value.copy(taskName = name)

        viewModelScope.launch {
            repository.save(
                PomodoroSettingEntity(
                    taskName = name,
                    focusDuration = focusDuration,
                    shortBreakDuration = shortBreakDuration,
                    longBreakDuration = longBreakDuration,
                    totalSection = totalSection
                )
            )
        }
    }



    init {
        viewModelScope.launch {
            repository.settingFlow.collect { setting ->
                if (setting != null) {
                    applySetting(setting)
                } else {
                    saveDefaultSetting()
                }
            }
        }
    }
}
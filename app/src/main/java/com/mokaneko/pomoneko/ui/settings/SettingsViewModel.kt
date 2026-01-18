package com.mokaneko.pomoneko.ui.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mokaneko.pomoneko.data.local.PomodoroSettingEntity
import com.mokaneko.pomoneko.data.repository.PomodoroRepository
import com.mokaneko.pomoneko.ui.timer.state.TimerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val MIN_DURATION = 1
private const val MAX_DURATION = 120

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: PomodoroRepository
) : ViewModel() {


    private val _uiState = mutableStateOf(SettingsUiState())
    val uiState: State<SettingsUiState> = _uiState
    private val _isTimerRunning = mutableStateOf(false)
    val isTimerRunning: State<Boolean> = _isTimerRunning


    private fun clampDuration(value: Int): Int {
        return value.coerceIn(MIN_DURATION, MAX_DURATION)
    }

    private fun updateAndSave(
        focus: Int,
        short: Int,
        long: Int
    ) {
        _uiState.value = _uiState.value.copy(
            focusDuration = focus,
            shortBreakDuration = short,
            longBreakDuration = long
        )

        viewModelScope.launch {
            repository.save(
                PomodoroSettingEntity(
                    taskName = "This cat needs a name",
                    focusDuration = focus * 60,
                    shortBreakDuration = short * 60,
                    longBreakDuration = long * 60,
                    totalSection = _uiState.value.totalSection
                )
            )
        }
    }


    fun updateFocusDuration(delta: Int) {
        if (_isTimerRunning.value) return

        val newValue = clampDuration(_uiState.value.focusDuration + delta)
        updateAndSave(
            focus = newValue,
            short = _uiState.value.shortBreakDuration,
            long = _uiState.value.longBreakDuration
        )
    }

    fun updateShortBreakDuration(delta: Int) {
        if (_isTimerRunning.value) return

        val newValue = clampDuration(_uiState.value.shortBreakDuration + delta)
        updateAndSave(
            focus = _uiState.value.focusDuration,
            short = newValue,
            long = _uiState.value.longBreakDuration
        )
    }

    fun updateLongBreakDuration(delta: Int) {
        if (_isTimerRunning.value) return

        val newValue = clampDuration(_uiState.value.longBreakDuration + delta)
        updateAndSave(
            focus = _uiState.value.focusDuration,
            short = _uiState.value.shortBreakDuration,
            long = newValue
        )
    }


    init {
        viewModelScope.launch {
            repository.timerState.collect { state ->
                _isTimerRunning.value = (state == TimerState.RUNNING)
            }
        }
        viewModelScope.launch{
            repository.settingFlow.collect { setting ->
                if (setting != null) {
                    _uiState.value = SettingsUiState(
                        focusDuration = setting.focusDuration / 60,
                        shortBreakDuration = setting.shortBreakDuration / 60,
                        longBreakDuration = setting.longBreakDuration / 60,
                        totalSection = setting.totalSection
                    )
                }
            }
        }
    }
}

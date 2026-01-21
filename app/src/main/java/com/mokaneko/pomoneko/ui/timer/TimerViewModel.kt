package com.mokaneko.pomoneko.ui.timer

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mokaneko.pomoneko.data.local.PomodoroPhase
import com.mokaneko.pomoneko.data.local.PomodoroSettingEntity
import com.mokaneko.pomoneko.data.repository.PomodoroRepository
import com.mokaneko.pomoneko.domain.event.TimerEvent
import com.mokaneko.pomoneko.ui.timer.state.TimerState
import com.mokaneko.pomoneko.ui.timer.state.TimerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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

    private val _event = MutableSharedFlow<TimerEvent>()
    val event = _event.asSharedFlow()

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
    private var autoStartSession: Boolean = true
    private var vibrationEnabled: Boolean = true


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
        autoStartSession = setting.autoStartSession
        vibrationEnabled = setting.vibrationEnabled

        if (_uiState.value.taskName != setting.taskName) {
            _uiState.value = _uiState.value.copy(
                taskName = setting.taskName
            )
        }

        val durationChanged =
            focusDuration != setting.focusDuration ||
                    shortBreakDuration != setting.shortBreakDuration ||
                    longBreakDuration != setting.longBreakDuration ||
                    totalSection != setting.totalSection

        focusDuration = setting.focusDuration
        shortBreakDuration = setting.shortBreakDuration
        longBreakDuration = setting.longBreakDuration
        totalSection = setting.totalSection

        if (durationChanged && uiState.value.timerState == TimerState.STOPPED) {
            currentPhase = PomodoroPhase.FOCUS
            currentSection = 1
            totalSeconds = focusDuration
            remainingSeconds = totalSeconds
            updateUiState()
        }
    }
    private suspend fun saveDefaultSetting() {
        repository.save(
            PomodoroSettingEntity(
                taskName = "This cat needs a name",
                focusDuration = 25*60,
                shortBreakDuration = 5*60,
                longBreakDuration = 15*60,
                totalSection = 4,
                autoStartSession = true,
                vibrationEnabled = true,
                stayAwake = false
            )
        )
    }

    private suspend fun notifyPhaseChanged() {
        if (vibrationEnabled) {
            _event.emit(TimerEvent.PhaseChanged(currentPhase))
        }
    }




    /* ------------ functions ------------ */

    val stayAwake = repository.settingFlow
        .filterNotNull()
        .map { it.stayAwake }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    private fun resetAndStop() {
        timerJob?.cancel()
        timerJob = null

        currentPhase = PomodoroPhase.FOCUS
        currentSection = 1
        totalSeconds = focusDuration
        remainingSeconds = totalSeconds

        repository.updateTimerState(TimerState.STOPPED)

        _uiState.value = _uiState.value.copy(
            timerState = TimerState.STOPPED
        )

        updateUiState()
    }


    private fun resetToFirstSession() {
        currentSection = 1
        currentPhase = PomodoroPhase.FOCUS
        totalSeconds = focusDuration
        remainingSeconds = totalSeconds
        updateUiState()
    }

    private fun moveToNextPhase(): Boolean {
        return when (currentPhase) {
            PomodoroPhase.FOCUS -> {
                if (currentSection == totalSection) {
                    currentPhase = PomodoroPhase.LONG_BREAK
                    totalSeconds = longBreakDuration
                } else {
                    currentPhase = PomodoroPhase.SHORT_BREAK
                    totalSeconds = shortBreakDuration
                }
                remainingSeconds = totalSeconds
                updateUiState()
                viewModelScope.launch {notifyPhaseChanged()}
                true
            }
            PomodoroPhase.SHORT_BREAK -> {
                currentSection++
                currentPhase = PomodoroPhase.FOCUS
                totalSeconds = focusDuration
                remainingSeconds = totalSeconds
                updateUiState()
                viewModelScope.launch {notifyPhaseChanged()}
                true
            }
            PomodoroPhase.LONG_BREAK -> {
                if(autoStartSession) {
                    resetToFirstSession()
                    viewModelScope.launch {notifyPhaseChanged()}
                    true
                } else {
                    resetAndStop()
                    false
                }
            }
        }
    }

    fun onPlay() {
        if (timerJob != null) return
        val firstSession: Boolean = uiState.value.timerState == TimerState.STOPPED

        repository.updateTimerState(TimerState.RUNNING)
        _uiState.value = uiState.value.copy(
            timerState = TimerState.RUNNING
        )

        timerJob = viewModelScope.launch {
            if (firstSession) delay(500)
            while (remainingSeconds > 0) {
                delay(1000)
                remainingSeconds--
                updateUiState()
            }
            timerJob = null
            val shouldContinue = moveToNextPhase()
            if (shouldContinue) {
                onPlay()
            }
        }
    }
    fun onPause() {
        timerJob?.cancel()
        timerJob = null
        repository.updateTimerState(TimerState.PAUSED)
        _uiState.value = _uiState.value.copy(timerState = TimerState.PAUSED)
    }
    fun onReset() {
        timerJob?.cancel()
        timerJob = null

        repository.updateTimerState(TimerState.STOPPED)

        currentPhase = PomodoroPhase.FOCUS
        currentSection = 1
        totalSeconds = focusDuration
        remainingSeconds = totalSeconds

        _uiState.value = _uiState.value.copy(
            timerState = TimerState.STOPPED
        )

        updateUiState()
    }

    fun updateTaskName(name: String) {
        _uiState.value = _uiState.value.copy(taskName = name)

        viewModelScope.launch {
            val setting = repository.settingFlow.first()
            if (setting != null) {
                repository.save(
                    setting.copy(taskName = name)
                )
            }
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
package com.mokaneko.pomoneko.data.repository

import com.mokaneko.pomoneko.data.local.PomodoroSettingDao
import com.mokaneko.pomoneko.data.local.PomodoroSettingEntity
import com.mokaneko.pomoneko.ui.timer.state.TimerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PomodoroRepository(
    private val dao: PomodoroSettingDao
){
    val settingFlow = dao.observeSetting()
    private val _timerState = MutableStateFlow(TimerState.STOPPED)
    val timerState: StateFlow<TimerState> = _timerState
    suspend fun save(setting: PomodoroSettingEntity) {
        dao.saveSetting(setting)
    }

    fun updateTimerState(state: TimerState) {
        _timerState.value = state
    }
}
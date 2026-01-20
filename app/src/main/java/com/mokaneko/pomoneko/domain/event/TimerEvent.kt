package com.mokaneko.pomoneko.domain.event

import com.mokaneko.pomoneko.data.local.PomodoroPhase

sealed class TimerEvent {
    data class PhaseChanged(val phase: PomodoroPhase) : TimerEvent()
}
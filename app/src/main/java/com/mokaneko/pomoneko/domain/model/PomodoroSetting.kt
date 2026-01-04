package com.mokaneko.pomoneko.domain.model

data class PomodoroSetting(
    val autoStartPomodoro: Boolean,
    val autoStartBreak: Boolean,
    val vibration: Boolean,
    val alarm: Boolean
)

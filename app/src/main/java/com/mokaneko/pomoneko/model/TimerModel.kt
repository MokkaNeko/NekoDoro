package com.mokaneko.pomoneko.model

data class TimerModel(
    val timerPomodoro: Long,
    val timerShortBreak: Long,
    val timerLongBreak: Long,
    val pomodoroCounts: Int
)

package com.mokaneko.pomoneko.model

data class SettingModel(
    val autoStartPomodoro: Boolean,
    val autoStartBreak: Boolean,
    val vibration: Boolean,
    val alarm: Boolean
)

package com.mokaneko.pomoneko.ui.settings

data class SettingsUiState(
    val focusDuration: Int = 25,
    val shortBreakDuration: Int = 5,
    val longBreakDuration: Int = 15,
    val totalSection: Int = 4,
    val autoStartSession: Boolean = true
)
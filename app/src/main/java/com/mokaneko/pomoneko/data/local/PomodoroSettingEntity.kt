package com.mokaneko.pomoneko.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pomodoro_setting")
data class PomodoroSettingEntity(
    @PrimaryKey val id: Int = 0,
    val taskName: String,
    val focusDuration: Int,
    val shortBreakDuration: Int,
    val longBreakDuration: Int,
    val totalSection: Int,
    val autoStartSession: Boolean = true,
    val vibrationEnabled: Boolean = true,
    val stayAwake: Boolean = false
)
package com.mokaneko.pomoneko.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PomodoroSettingEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pomodoroSettingDao(): PomodoroSettingDao
}
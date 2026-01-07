package com.mokaneko.pomoneko.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PomodoroSettingDao {
    @Query("SELECT * FROM pomodoro_setting WHERE id = 0")
    fun observeSetting(): Flow<PomodoroSettingEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSetting(setting: PomodoroSettingEntity)
}
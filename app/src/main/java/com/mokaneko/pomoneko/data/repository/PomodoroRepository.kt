package com.mokaneko.pomoneko.data.repository

import com.mokaneko.pomoneko.data.local.PomodoroSettingDao
import com.mokaneko.pomoneko.data.local.PomodoroSettingEntity

class PomodoroRepository(
    private val dao: PomodoroSettingDao
){
    val settingFlow = dao.observeSetting()
    suspend fun save(setting: PomodoroSettingEntity) {
        dao.saveSetting(setting)
    }
}
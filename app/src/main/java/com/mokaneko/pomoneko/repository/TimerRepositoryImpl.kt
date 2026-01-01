package com.mokaneko.pomoneko.repository

import javax.inject.Inject

class TimerRepositoryImpl @Inject constructor() : TimerRepository {
    override fun start() { /* timer logic */ }
    override fun pause() { /* timer logic */ }
    override fun reset() { /* timer logic */ }
}
package com.mokaneko.pomoneko.repository

interface TimerRepository {
    fun start()
    fun pause()
    fun reset()
}
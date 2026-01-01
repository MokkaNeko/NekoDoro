package com.mokaneko.pomoneko.di

import com.mokaneko.pomoneko.repository.TimerRepository
import com.mokaneko.pomoneko.repository.TimerRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTimerRepository(): TimerRepository {
        return TimerRepositoryImpl()
    }
}

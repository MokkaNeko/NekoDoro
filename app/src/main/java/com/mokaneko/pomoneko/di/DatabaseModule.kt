package com.mokaneko.pomoneko.di

import android.content.Context
import androidx.room.Room
import com.mokaneko.pomoneko.data.local.AppDatabase
import com.mokaneko.pomoneko.data.local.PomodoroSettingDao
import com.mokaneko.pomoneko.data.repository.PomodoroRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "pomodoro_db"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun providePomodoroSettingDao(db: AppDatabase): PomodoroSettingDao = db.pomodoroSettingDao()
    @Provides
    @Singleton
    fun providePomodoroRepository(
        dao: PomodoroSettingDao
    ): PomodoroRepository = PomodoroRepository(dao)
}

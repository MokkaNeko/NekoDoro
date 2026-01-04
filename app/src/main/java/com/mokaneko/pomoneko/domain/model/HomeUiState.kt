package com.mokaneko.pomoneko.domain.model

data class HomeUiState(
    val taskName: String = "This cat needs a name",
    val progress: Float = 1f,
    val timeText: String = "25:00",
    val section: Int = 4,
    val currentSection: Int = 1,
    val currentSectionName: SectionName = SectionName.Pomodoro,
    val timerState: TimerState = TimerState.Stopped
)

enum class TimerState {
    Running,
    Paused,
    Stopped
}

enum class SectionName {
    Pomodoro,
    ShortBreak,
    LongBreak
}

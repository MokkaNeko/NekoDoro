package com.mokaneko.pomoneko.viewmodel

import androidx.lifecycle.ViewModel
import com.mokaneko.pomoneko.domain.model.HomeUiState
import com.mokaneko.pomoneko.domain.model.TimerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onPlayPause() {
        _uiState.update { state ->
            state.copy(
                timerState = if (state.timerState == TimerState.Running) {
                    TimerState.Paused
                } else {
                    TimerState.Running
                }
            )
        }
    }

    fun onReset() {
        _uiState.value = HomeUiState()
    }
}
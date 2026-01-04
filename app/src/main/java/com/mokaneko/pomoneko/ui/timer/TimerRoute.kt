package com.mokaneko.pomoneko.ui.timer

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TimerRoute(
    viewModel: TimerViewModel = viewModel()
) {
    TimerScreen(
        uiState = viewModel.uiState.value,
        onPlay = viewModel::onPlay,
        onPause = viewModel::onPause,
        onReset = viewModel::onReset
    )
}
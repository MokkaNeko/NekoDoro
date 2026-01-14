package com.mokaneko.pomoneko.ui.timer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TimerRoute(
    modifier: Modifier = Modifier,
    viewModel: TimerViewModel = hiltViewModel()
) {
    TimerScreen(
        uiState = viewModel.uiState.value,
        onPlay = viewModel::onPlay,
        onPause = viewModel::onPause,
        onReset = viewModel::onReset
    )
}
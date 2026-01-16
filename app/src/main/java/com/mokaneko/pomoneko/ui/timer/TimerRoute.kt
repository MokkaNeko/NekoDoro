package com.mokaneko.pomoneko.ui.timer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mokaneko.pomoneko.ui.navigation.Screen

@Composable
fun TimerRoute(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    TimerScreen(
        onOpenSettings = {
            navController.navigate(Screen.Setting.route)
        }
    )
}
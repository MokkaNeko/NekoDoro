package com.mokaneko.pomoneko.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mokaneko.pomoneko.ui.timer.TimerScreen

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
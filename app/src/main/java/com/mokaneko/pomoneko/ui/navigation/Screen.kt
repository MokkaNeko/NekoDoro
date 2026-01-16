package com.mokaneko.pomoneko.ui.navigation

sealed class Screen(val route: String) {
    object Timer : Screen("Timer")
    object Setting : Screen("Settings")
}
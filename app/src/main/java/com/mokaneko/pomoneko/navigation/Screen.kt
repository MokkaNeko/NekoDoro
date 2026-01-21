package com.mokaneko.pomoneko.navigation

sealed class Screen(val route: String) {
    object Timer : Screen("Timer")
    object Setting : Screen("Settings")
    object HowTo : Screen("howto")
    object HowToFromSettings : Screen("howto_settings")
}
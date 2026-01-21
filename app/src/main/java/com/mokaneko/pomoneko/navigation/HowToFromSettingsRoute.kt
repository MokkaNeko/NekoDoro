package com.mokaneko.pomoneko.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.mokaneko.pomoneko.ui.tutorial.HowToCarousel
import com.mokaneko.pomoneko.ui.tutorial.howToPages

@Composable
fun HowToFromSettingsRoute(
    navController: NavController
) {
    HowToCarousel(
        pages = howToPages,
        onFinish = {
            navController.popBackStack()
        }
    )
}

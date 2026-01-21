package com.mokaneko.pomoneko.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.mokaneko.pomoneko.ui.tutorial.HowToCarousel
import com.mokaneko.pomoneko.ui.tutorial.howToPages
import com.mokaneko.pomoneko.util.TutorialPreferences

@Composable
fun HowToRoute(
    navController: NavController
) {
    val context = LocalContext.current
    HowToCarousel(
        pages = howToPages,
        onFinish = {
            TutorialPreferences.setTutorialDone(context)

            navController.navigate(Screen.Timer.route) {
                popUpTo(Screen.HowTo.route) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    )
}

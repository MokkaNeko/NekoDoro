package com.mokaneko.pomoneko

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mokaneko.pomoneko.navigation.HowToRoute
import com.mokaneko.pomoneko.navigation.Screen
import com.mokaneko.pomoneko.ui.settings.SettingsScreen
import com.mokaneko.pomoneko.ui.theme.PomoNekoTheme
import com.mokaneko.pomoneko.navigation.TimerRoute
import com.mokaneko.pomoneko.util.TutorialPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PomoNekoTheme {
                val navController = rememberNavController()
                val context = LocalContext.current
                val startDestination = remember {
                    if (TutorialPreferences.isTutorialDone(context)) {
                        Screen.Timer.route
                    } else {
                        Screen.HowTo.route
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = startDestination,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.HowTo.route) {
                            HowToRoute(navController)
                        }
                        composable(Screen.Timer.route) {
                            TimerRoute(navController)
                        }
                        composable(Screen.Setting.route) {
                            SettingsScreen(
                                onBack = { navController.popBackStack() }
                            )
                        }
                    }

                }
            }
        }
    }
}
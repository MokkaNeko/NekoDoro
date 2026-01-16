package com.mokaneko.pomoneko

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mokaneko.pomoneko.ui.navigation.Screen
import com.mokaneko.pomoneko.ui.settings.SettingsScreen
import com.mokaneko.pomoneko.ui.theme.PomoNekoTheme
import com.mokaneko.pomoneko.ui.timer.TimerRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PomoNekoTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Timer.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Timer.route){
                            TimerRoute(navController)
                        }
                        composable(Screen.Setting.route){
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
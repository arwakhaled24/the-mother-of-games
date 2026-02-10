package com.example.themotherofgames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.themotherofgames.presentation.navigation.NavGraph
import com.example.themotherofgames.ui.theme.TheMotherOfGamesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheMotherOfGamesTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

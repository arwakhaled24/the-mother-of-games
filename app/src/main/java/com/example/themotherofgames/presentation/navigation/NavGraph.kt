package com.example.themotherofgames.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.themotherofgames.presentation.screens.GameDetailsScreen
import com.example.themotherofgames.presentation.screens.GamesScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Games.route
    ) {
        composable(route = Screen.Games.route) {
            GamesScreen(
                onGameClick = { gameId ->
                    navController.navigate(Screen.GameDetails.createRoute(gameId))
                }
            )
        }
        composable(
            route = Screen.GameDetails.route,
            arguments = listOf(
                navArgument("gameId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val gameId = backStackEntry.arguments?.getInt("gameId") ?: 0
            GameDetailsScreen(
                gameId = gameId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

package com.marcosmontiel.gameslearning.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marcosmontiel.gameslearning.presentation.navigation.RootRoutes.Home
import com.marcosmontiel.gameslearning.presentation.screens.home.HomeScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Graph.AUTHENTICATION,
        route = Graph.ROOT
    ) {

        authNavGraph(navController = navController)

        composable(route = Home.route) {
            HomeScreen()
        }

    }
}

sealed class RootRoutes(val route: String) {

    object Home: RootRoutes(route = "home")

}

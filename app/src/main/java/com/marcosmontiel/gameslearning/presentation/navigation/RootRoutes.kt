package com.marcosmontiel.gameslearning.presentation.navigation

sealed class RootRoutes(val route: String) {

    object Home: RootRoutes(route = "home")

}

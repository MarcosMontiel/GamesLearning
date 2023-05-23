package com.marcosmontiel.gameslearning.presentation.navigation

sealed class AuthRoutes(val route: String) {

    object Login : AuthRoutes(route = "login")

    object Register : AuthRoutes(route = "register")

}

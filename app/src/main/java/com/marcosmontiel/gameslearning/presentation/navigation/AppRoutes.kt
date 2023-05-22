package com.marcosmontiel.gameslearning.presentation.navigation

sealed class AppRoutes(val route: String) {

    object Login : AppRoutes(route = "login")

    object Register : AppRoutes(route = "register")

    object Profile : AppRoutes(route = "profile")

    object ProfileEdit : AppRoutes(route = "profile/edit/{profile}") {
        fun createArgs(profile: String): String = "profile/edit/$profile"
    }

}

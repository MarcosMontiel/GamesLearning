package com.marcosmontiel.gameslearning.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.marcosmontiel.gameslearning.presentation.navigation.AuthRoutes.Login
import com.marcosmontiel.gameslearning.presentation.screens.login.LoginScreen
import com.marcosmontiel.gameslearning.presentation.screens.register.RegisterScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(startDestination = Login.route, route = Graph.AUTHENTICATION) {

        composable(route = Login.route) {
            LoginScreen(navController = navController)
        }

        composable(route = AuthRoutes.Register.route) {
            RegisterScreen(navController = navController)
        }

    }
}

sealed class AuthRoutes(val route: String) {

    object Login : AuthRoutes(route = "login")

    object Register : AuthRoutes(route = "register")

}

package com.marcosmontiel.gameslearning.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.marcosmontiel.gameslearning.presentation.navigation.AppRoutes.*
import com.marcosmontiel.gameslearning.presentation.screens.login.LoginScreen
import com.marcosmontiel.gameslearning.presentation.screens.profile.ProfileScreen
import com.marcosmontiel.gameslearning.presentation.screens.profile_update.ProfileEditScreen
import com.marcosmontiel.gameslearning.presentation.screens.register.RegisterScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Login.route) {

        composable(route = Login.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Register.route) {
            RegisterScreen(navController = navController)
        }

        composable(route = Profile.route) {
            ProfileScreen(navController = navController)
        }

        composable(
            route = ProfileEdit.route,
            arguments = listOf(
                navArgument("profile") { type = NavType.StringType }
            )
        ) {
            ProfileEditScreen(navController = navController)
        }

    }
}

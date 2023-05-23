package com.marcosmontiel.gameslearning.presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.marcosmontiel.gameslearning.presentation.navigation.DetailsRoutes.ProfileEdit
import com.marcosmontiel.gameslearning.presentation.screens.profile_edit.ProfileEditScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(startDestination = ProfileEdit.route, route = Graph.DETAILS) {

        composable(
            route = ProfileEdit.route,
            arguments = listOf(
                navArgument(name = "profile") { type = NavType.StringType }
            )
        ) {
            ProfileEditScreen(navController = navController)
        }

    }
}

sealed class DetailsRoutes(val route: String) {

    object ProfileEdit : DetailsRoutes(route = "profile/edit/{profile}") {
        fun createArgs(profile: String): String = "profile/edit/$profile"
    }

}

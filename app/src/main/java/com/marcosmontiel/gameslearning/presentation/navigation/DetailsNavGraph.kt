package com.marcosmontiel.gameslearning.presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.marcosmontiel.gameslearning.presentation.navigation.DetailsRoutes.*
import com.marcosmontiel.gameslearning.presentation.screens.detail_post.DetailPostScreen
import com.marcosmontiel.gameslearning.presentation.screens.edit_post.EditPostScreen
import com.marcosmontiel.gameslearning.presentation.screens.new_post.NewPostScreen
import com.marcosmontiel.gameslearning.presentation.screens.profile_edit.ProfileEditScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(startDestination = ProfileEdit.route, route = Graph.DETAILS) {

        composable(route = NewPost.route) {
            NewPostScreen(navController = navController)
        }

        composable(
            route = EditPost.route,
            arguments = listOf(
                navArgument(name = "post") { type = NavType.StringType }
            )
        ) {
            EditPostScreen(navController = navController)
        }

        composable(
            route = DetailPost.route,
            arguments = listOf(
                navArgument(name = "post") { type = NavType.StringType }
            )
        ) {
            DetailPostScreen(navController = navController)
        }

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

    object NewPost : DetailsRoutes(route = "post/new")

    object EditPost : DetailsRoutes(route = "post/edit/{post}") {
        fun createArgs(post: String): String = "post/edit/$post"
    }

    object DetailPost : DetailsRoutes(route = "post/detail/{post}") {
        fun createArgs(post: String): String = "post/detail/$post"
    }

    object ProfileEdit : DetailsRoutes(route = "profile/edit/{profile}") {
        fun createArgs(profile: String): String = "profile/edit/$profile"
    }

}

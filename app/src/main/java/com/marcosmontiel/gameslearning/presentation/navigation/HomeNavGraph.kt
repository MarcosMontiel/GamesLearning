package com.marcosmontiel.gameslearning.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marcosmontiel.gameslearning.presentation.navigation.HomeRoutes.*
import com.marcosmontiel.gameslearning.presentation.screens.my_posts.MyPostsScreen
import com.marcosmontiel.gameslearning.presentation.screens.posts.PostsScreen
import com.marcosmontiel.gameslearning.presentation.screens.profile.ProfileScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Posts.route,
        route = Graph.HOME
    ) {

        composable(route = Posts.route) {
            PostsScreen(navController = navController)
        }

        composable(route = MyPosts.route) {
            MyPostsScreen(navController = navController)
        }

        composable(route = Profile.route) {
            ProfileScreen(navController = navController)
        }

    }
}

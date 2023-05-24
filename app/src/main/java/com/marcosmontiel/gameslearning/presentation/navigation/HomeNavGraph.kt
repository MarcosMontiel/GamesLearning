package com.marcosmontiel.gameslearning.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marcosmontiel.gameslearning.presentation.navigation.HomeRoutes.*
import com.marcosmontiel.gameslearning.presentation.screens.my_posts.MyPostsScreen
import com.marcosmontiel.gameslearning.presentation.screens.posts.PostsScreen
import com.marcosmontiel.gameslearning.presentation.screens.profile.ProfileScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
@ExperimentalCoroutinesApi
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Posts.route,
        route = Graph.HOME
    ) {

        composable(route = MyPosts.route) {
            MyPostsScreen(navController = navController)
        }

        composable(route = Posts.route) {
            PostsScreen(navController = navController)
        }

        composable(route = Profile.route) {
            ProfileScreen(navController = navController)
        }

        detailsNavGraph(navController = navController)

    }
}

sealed class HomeRoutes(val icon: ImageVector, val title: String, val route: String) {

    object MyPosts : HomeRoutes(
        icon = Icons.Rounded.List,
        title = "Mis posts",
        route = "my_posts"
    )

    object Posts : HomeRoutes(
        icon = Icons.Rounded.List,
        title = "Posts",
        route = "posts"
    )

    object Profile : HomeRoutes(
        icon = Icons.Rounded.Person,
        title = "Mi perfil",
        route = "profile"
    )

}

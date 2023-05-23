package com.marcosmontiel.gameslearning.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class HomeRoutes(val icon: ImageVector, val title: String, val route: String) {

    object Posts : HomeRoutes(
        icon = Icons.Rounded.List,
        title = "Publicaciones",
        route = "posts"
    )

    object MyPosts : HomeRoutes(
        icon = Icons.Outlined.List,
        title = "Mis publicaciones",
        route = "my_posts"
    )

    object Profile : HomeRoutes(
        icon = Icons.Rounded.Person,
        title = "Mi perfil",
        route = "profile"
    )

}

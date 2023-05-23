package com.marcosmontiel.gameslearning.presentation.screens.my_posts

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun MyPostsScreen(navController: NavHostController) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {},
        content = {
            Text(text = "MyPostsScreen")
        },
        bottomBar = {}
    )
}

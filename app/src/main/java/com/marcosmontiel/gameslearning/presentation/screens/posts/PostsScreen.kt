package com.marcosmontiel.gameslearning.presentation.screens.posts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.presentation.screens.posts.components.DeleteLikeView
import com.marcosmontiel.gameslearning.presentation.screens.posts.components.LikeView
import com.marcosmontiel.gameslearning.presentation.screens.posts.components.PostsView

@Composable
fun PostsScreen(navController: NavHostController) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {},
        content = { paddingValues ->

            PostsView(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                paddingValues = paddingValues
            )

        },
        bottomBar = {}
    )

    LikeView(modifier = Modifier.fillMaxSize())

    DeleteLikeView(modifier = Modifier.fillMaxSize())
}

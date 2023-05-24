package com.marcosmontiel.gameslearning.presentation.screens.new_post

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.presentation.components.DefaultTopBar
import com.marcosmontiel.gameslearning.presentation.screens.new_post.components.NewPostContent

@Composable
fun NewPostScreen(navController: NavHostController) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {

            DefaultTopBar(
                title = "Nuevo post",
                navController = navController
            )

        },
        content = { paddingValues ->

            NewPostContent(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                paddingValues = paddingValues
            )

        },
        bottomBar = {}
    )
}

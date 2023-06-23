package com.marcosmontiel.gameslearning.presentation.screens.detail_post

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.presentation.screens.detail_post.components.DetailPostContent

@Composable
fun DetailPostScreen(navController: NavHostController) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {},
        content = { paddingValues ->

            DetailPostContent(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                paddingValues = paddingValues
            )

        },
        bottomBar = {}
    )
}

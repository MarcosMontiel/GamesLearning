package com.marcosmontiel.gameslearning.presentation.screens.my_posts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.presentation.screens.my_posts.components.MyPostsContent

@Composable
fun MyPostsScreen(navController: NavHostController) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {},
        content = { paddingValues ->

            MyPostsContent(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                paddingValues = paddingValues
            )

        },
        bottomBar = {}
    )
}

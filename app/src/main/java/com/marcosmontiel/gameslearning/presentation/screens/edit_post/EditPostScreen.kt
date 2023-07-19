package com.marcosmontiel.gameslearning.presentation.screens.edit_post

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.presentation.components.DefaultTopBar
import com.marcosmontiel.gameslearning.presentation.screens.edit_post.components.EditPostContent
import com.marcosmontiel.gameslearning.presentation.screens.edit_post.components.EditPostView

@Composable
fun EditPostScreen(navController: NavHostController) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {

            DefaultTopBar(
                title = "Editar post",
                navController = navController
            )

        },
        content = { paddingValues ->

            EditPostContent(
                modifier = Modifier.fillMaxSize(),
                paddingValues = paddingValues
            )

        },
        bottomBar = {}
    )

    EditPostView(
        modifier = Modifier.fillMaxSize(),
        navController = navController
    )
}

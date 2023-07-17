package com.marcosmontiel.gameslearning.presentation.screens.my_posts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.presentation.components.DefaultFloatingActionButton
import com.marcosmontiel.gameslearning.presentation.navigation.DetailsRoutes.NewPost
import com.marcosmontiel.gameslearning.presentation.screens.my_posts.components.MyPostsView

@Composable
fun MyPostsScreen(navController: NavHostController) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {},
        content = { paddingValues ->

            MyPostsView(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                paddingValues = paddingValues
            )

        },
        bottomBar = {},
        floatingActionButton = {

            DefaultFloatingActionButton(
                icon = Icons.Rounded.Add,
                onClickAction = {
                    navController.navigate(NewPost.route)
                }
            )

        }
    )
}

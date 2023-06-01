package com.marcosmontiel.gameslearning.presentation.screens.posts.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.presentation.components.DefaultProgressIndicator
import com.marcosmontiel.gameslearning.presentation.screens.posts.PostsViewModel

@Composable
fun PostsView(
    modifier: Modifier,
    viewModel: PostsViewModel = hiltViewModel(),
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    val postsResponse = viewModel.postsResponse

    Box(
        modifier = modifier,
        content = {

            postsResponse.let { stateFlow ->
                when (stateFlow) {
                    Response.Loading -> {

                        DefaultProgressIndicator()

                    }

                    is Response.Failure -> {

                        val message: String = stateFlow.exception?.message ?: "Unknown exception"
                        Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG).show()

                    }

                    is Response.Success -> {

                        PostsContent(
                            modifier = Modifier.fillMaxSize(),
                            navController = navController,
                            paddingValues = paddingValues,
                            posts = stateFlow.data
                        )

                    }

                    else -> {}
                }
            }

        }
    )
}

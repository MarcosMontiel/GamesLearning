package com.marcosmontiel.gameslearning.presentation.screens.posts.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.presentation.components.DefaultProgressIndicator
import com.marcosmontiel.gameslearning.presentation.screens.posts.PostsViewModel

@Composable
fun DeleteLikeView(
    modifier: Modifier,
    viewModel: PostsViewModel = hiltViewModel()
) {
    val dislikeResponse = viewModel.dislikeResponse

    Box(
        modifier = modifier,
        content = {

            dislikeResponse.let { stateFlow ->
                when (stateFlow) {
                    Response.Loading -> {

                        DefaultProgressIndicator()

                    }

                    is Response.Failure -> {

                        val message: String = stateFlow.exception?.message ?: "Unknown exception"
                        Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG).show()

                    }

                    else -> {}
                }
            }

        }
    )
}

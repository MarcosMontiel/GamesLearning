package com.marcosmontiel.gameslearning.presentation.screens.edit_post.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.presentation.components.DefaultProgressIndicator
import com.marcosmontiel.gameslearning.presentation.screens.edit_post.EditPostViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
@ExperimentalCoroutinesApi
fun EditPostView(
    modifier: Modifier,
    viewModel: EditPostViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val postResponse = viewModel.postResponse

    Box(
        modifier = modifier,
        content = {

            postResponse.let { stateFlow ->
                when (stateFlow) {
                    Response.Loading -> {

                        DefaultProgressIndicator()

                    }

                    is Response.Failure -> {

                        val message: String = stateFlow.exception?.message ?: "Unknown exception"
                        Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG).show()

                    }

                    is Response.Success -> {}

                    else -> {}
                }
            }

        }
    )
}

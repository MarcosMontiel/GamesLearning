package com.marcosmontiel.gameslearning.presentation.screens.profile_edit.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.presentation.components.DefaultProgressIndicator
import com.marcosmontiel.gameslearning.presentation.screens.profile_edit.ProfileEditViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
@ExperimentalCoroutinesApi
fun ProfileEditImageView(
    modifier: Modifier,
    viewModel: ProfileEditViewModel = hiltViewModel()
) {
    val imageResponse by viewModel.imageResponse.collectAsState()

    Box(modifier = modifier) {
        imageResponse.let { stateFlow ->
            when (stateFlow) {
                Response.Loading -> {

                    DefaultProgressIndicator()

                }

                is Response.Failure -> {

                    viewModel.enableFields()

                    val message: String = stateFlow.exception?.message ?: "Unknown exception"
                    Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG).show()

                }

                is Response.Success -> {

                    viewModel.onUpdateProfile(imageUrl = stateFlow.data)

                }

                else -> {}
            }
        }
    }
}

package com.marcosmontiel.gameslearning.presentation.screens.register.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.presentation.components.DefaultProgressIndicator
import com.marcosmontiel.gameslearning.presentation.navigation.AuthRoutes.Login
import com.marcosmontiel.gameslearning.presentation.navigation.HomeRoutes.Profile
import com.marcosmontiel.gameslearning.presentation.screens.register.RegisterViewModel

@Composable
fun RegisterView(
    modifier: Modifier,
    viewModel: RegisterViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val registerResponse by viewModel.registerResponse.collectAsState()

    Box(modifier = modifier) {
        registerResponse.let { stateFlow ->
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

                    LaunchedEffect(Unit) {
                        viewModel.createProfile()
                        navController.popBackStack(route = Login.route, inclusive = true)
                        navController.navigate(route = Profile.route)
                    }

                }

                else -> {}
            }
        }
    }
}

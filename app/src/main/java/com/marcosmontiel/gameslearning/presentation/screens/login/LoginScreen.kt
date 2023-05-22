package com.marcosmontiel.gameslearning.presentation.screens.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.presentation.screens.login.components.LoginContent
import com.marcosmontiel.gameslearning.presentation.screens.login.components.LoginView

@Composable
fun LoginScreen(navController: NavHostController) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {},
        content = { paddingValues ->

            LoginContent(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                paddingValues = paddingValues
            )

        },
        bottomBar = {}
    )

    LoginView(
        modifier = Modifier.fillMaxSize(),
        navController = navController
    )
}

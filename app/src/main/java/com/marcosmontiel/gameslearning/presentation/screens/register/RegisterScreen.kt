package com.marcosmontiel.gameslearning.presentation.screens.register

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.presentation.components.DefaultTopBar
import com.marcosmontiel.gameslearning.presentation.screens.register.components.RegisterContent
import com.marcosmontiel.gameslearning.presentation.screens.register.components.RegisterView

@Composable
fun RegisterScreen(navController: NavHostController) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {

            DefaultTopBar(
                title = "Nuevo usuario",
                navController = navController
            )

        },
        content = { paddingValues ->

            RegisterContent(
                modifier = Modifier.fillMaxSize(),
                paddingValues = paddingValues
            )

        },
        bottomBar = {}
    )

    RegisterView(
        modifier = Modifier.fillMaxSize(),
        navController = navController
    )
}

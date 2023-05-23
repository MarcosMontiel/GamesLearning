package com.marcosmontiel.gameslearning.presentation.screens.profile_edit

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.presentation.components.DefaultTopBar
import com.marcosmontiel.gameslearning.presentation.screens.profile_edit.components.ProfileEditContent
import com.marcosmontiel.gameslearning.presentation.screens.profile_edit.components.ProfileEditImageView
import com.marcosmontiel.gameslearning.presentation.screens.profile_edit.components.ProfileEditView
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
@ExperimentalCoroutinesApi
fun ProfileEditScreen(navController: NavHostController) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {

            DefaultTopBar(
                title = "Editar perfil",
                navController = navController
            )

        },
        content = { paddingValues ->

            ProfileEditContent(
                modifier = Modifier.fillMaxSize(),
                paddingValues = paddingValues
            )

        },
        bottomBar = {}
    )

    ProfileEditImageView(modifier = Modifier.fillMaxSize())

    ProfileEditView(
        modifier = Modifier.fillMaxSize(),
        navController = navController
    )
}

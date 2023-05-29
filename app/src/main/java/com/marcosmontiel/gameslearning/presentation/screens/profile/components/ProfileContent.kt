package com.marcosmontiel.gameslearning.presentation.screens.profile.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.R
import com.marcosmontiel.gameslearning.domain.model.User
import com.marcosmontiel.gameslearning.presentation.MainActivity
import com.marcosmontiel.gameslearning.presentation.components.*
import com.marcosmontiel.gameslearning.presentation.navigation.DetailsRoutes.ProfileEdit
import com.marcosmontiel.gameslearning.presentation.screens.profile.ProfileViewModel
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray500
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray900
import com.marcosmontiel.gameslearning.presentation.ui.theme.Red300
import com.marcosmontiel.gameslearning.presentation.ui.theme.Red500
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
@ExperimentalCoroutinesApi
fun ProfileContent(
    modifier: Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    val profile by viewModel.profileData.observeAsState(initial = User())

    Box(modifier = modifier.padding(paddingValues = paddingValues)) {

        DefaultBackgroundHeader(
            modifier = Modifier.align(Alignment.TopCenter),
            height = 218.dp,
            color = Gray900,
            content = {

                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = "background image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    alpha = 0.6f
                )

            }
        )

        ProfileHeaderContent(
            modifier = Modifier.align(Alignment.TopCenter),
            profileData = profile
        )

        ProfileCardContent(
            modifier = Modifier.align(Alignment.Center),
            profileData = profile,
            viewModel = viewModel,
            navController = navController
        )

    }
}

@Composable
fun ProfileHeaderContent(
    modifier: Modifier,
    profileData: User
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.size(56.dp))

        DefaultText(
            text = "BIENVENIDO",
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.size(72.dp))

        if (profileData.avatar.isNotEmpty()) {

            DefaultAsyncImage(
                modifier = Modifier.size(130.dp),
                fraction = 0.93f,
                image = profileData.avatar
            )

        } else {

            DefaultImage(
                modifier = Modifier.size(130.dp),
                fraction = 0.93f
            )

        }

    }
}

@Composable
@ExperimentalCoroutinesApi
fun ProfileCardContent(
    modifier: Modifier,
    profileData: User,
    viewModel: ProfileViewModel,
    navController: NavHostController
) {
    val activity: Activity? = LocalContext.current as? Activity

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.size(16.dp))

        DefaultText(
            text = profileData.username,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Normal
        )

        DefaultText(
            text = profileData.email,
            color = Gray500,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.body2
        )

        Spacer(modifier = Modifier.size(32.dp))

        DefaultButton(
            modifier = Modifier.fillMaxWidth(0.5f),
            title = "Editar Datos",
            onClickAction = {
                navController.navigate(
                    route = ProfileEdit.createArgs(profile = viewModel.convertLiveDataToJson())
                )
            }
        )

        DefaultButton(
            modifier = Modifier.fillMaxWidth(0.5f),
            backgroundColor = Red500,
            disabledBackground = Red300,
            title = "Cerrar Sesión",
            onClickAction = {
                viewModel.onLogout()
                activity?.finish()
                activity?.startActivity(Intent(activity, MainActivity::class.java))
            }
        )

    }
}

package com.marcosmontiel.gameslearning.presentation.screens.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.R
import com.marcosmontiel.gameslearning.domain.model.User
import com.marcosmontiel.gameslearning.presentation.components.DefaultAvatarAsyncImage
import com.marcosmontiel.gameslearning.presentation.components.DefaultAvatarImage
import com.marcosmontiel.gameslearning.presentation.components.DefaultButton
import com.marcosmontiel.gameslearning.presentation.navigation.AppRoutes.*
import com.marcosmontiel.gameslearning.presentation.screens.profile.ProfileViewModel
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray500
import com.marcosmontiel.gameslearning.presentation.ui.theme.Red300
import com.marcosmontiel.gameslearning.presentation.ui.theme.Red500

@Composable
fun ProfileContent(
    modifier: Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    val profile by viewModel.profileData.observeAsState(initial = User())

    Box(modifier = modifier.padding(paddingValues = paddingValues)) {

        ProfileHeaderBackground(
            modifier = Modifier.align(Alignment.TopCenter)
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
fun ProfileHeaderBackground(modifier: Modifier, height: Dp = 218.dp) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background image",
            modifier = Modifier
                .fillMaxWidth()
                .height(height),
            contentScale = ContentScale.Crop,
            alpha = 0.6f
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

        Text(
            text = "BIENVENIDO",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.size(72.dp))

        if (profileData.avatar.isNotEmpty()) {

            DefaultAvatarAsyncImage(
                fraction = 0.93f,
                image = profileData.avatar
            )

        } else {

            DefaultAvatarImage(fraction = 0.93f)

        }

    }
}

@Composable
fun ProfileCardContent(
    modifier: Modifier,
    profileData: User,
    viewModel: ProfileViewModel,
    navController: NavHostController
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = profileData.username,
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.body1
        )

        Text(
            text = profileData.email,
            color = Gray500,
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.body2
        )

        Spacer(modifier = Modifier.size(24.dp))

        DefaultButton(
            modifier = Modifier.fillMaxWidth(0.5f),
            title = "Editar datos",
            onClickAction = {
                navController.navigate(
                    route = ProfileEdit.createArgs(profile = viewModel.convertLiveDataToJson())
                )
            }
        )

        DefaultButton(
            modifier = Modifier.fillMaxWidth(0.5f),
            title = "Cerrar sesión",
            backgroundColor = Red500,
            disabledBackground = Red300,
            onClickAction = {
                viewModel.onLogout()
                navController.navigate(route = Login.route) {
                    popUpTo(route = Profile.route) { inclusive = true }
                }
            }
        )

    }
}

package com.marcosmontiel.gameslearning.presentation.screens.login.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.R
import com.marcosmontiel.gameslearning.presentation.components.DefaultButton
import com.marcosmontiel.gameslearning.presentation.components.DefaultPasswordTextField
import com.marcosmontiel.gameslearning.presentation.components.DefaultText
import com.marcosmontiel.gameslearning.presentation.components.DefaultTextField
import com.marcosmontiel.gameslearning.presentation.navigation.AuthRoutes.Register
import com.marcosmontiel.gameslearning.presentation.screens.login.LoginState
import com.marcosmontiel.gameslearning.presentation.screens.login.LoginViewModel
import com.marcosmontiel.gameslearning.presentation.ui.theme.Blue500
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray500
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray800

@Composable
fun LoginContent(
    modifier: Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    val state: LoginState = viewModel.state

    Box(modifier = modifier.padding(paddingValues = paddingValues)) {

        LoginHeaderContent(
            modifier = Modifier.align(Alignment.TopCenter),
            height = 280.dp
        )

        LoginCardContent(
            modifier = Modifier.align(Alignment.Center),
            state = state,
            viewModel = viewModel,
            background = Gray800
        )

        LoginFooterContent(
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController
        )

    }
}

@Composable
fun LoginHeaderContent(modifier: Modifier, height: Dp) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(Blue500)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ApplicationLogo(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                logo = R.drawable.logo
            )

            Spacer(modifier = Modifier.size(16.dp))

            DefaultText(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "FIREBASE MVVM"
            )

        }
    }
}

@Composable
fun ApplicationLogo(modifier: Modifier, @DrawableRes logo: Int) {
    Image(
        painter = painterResource(id = logo),
        contentDescription = "main logo",
        modifier = modifier
            .size(98.dp)
            .padding(top = 16.dp)
    )
}

@Composable
fun LoginCardContent(
    modifier: Modifier,
    state: LoginState,
    viewModel: LoginViewModel,
    background: Color
) {
    val email by viewModel.email.observeAsState(initial = "")
    val password by viewModel.password.observeAsState(initial = "")

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        backgroundColor = background,
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = modifier.padding(
                horizontal = 24.dp,
                vertical = 56.dp
            )
        ) {
            DefaultText(
                modifier = Modifier,
                text = "LOGIN"
            )

            Spacer(modifier = Modifier.size(8.dp))

            DefaultText(
                modifier = Modifier,
                text = "Inicia sesión para continuar",
                color = Gray500,
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.size(16.dp))

            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                isEnabled = state.fieldsStatus,
                placeholder = "Correo electrónico",
                value = email,
                keyboardType = KeyboardType.Email,
                onValueChangeAction = {
                    viewModel.onValueChange(email = it, password = password)
                }
            )

            Spacer(modifier = Modifier.size(16.dp))

            DefaultPasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                isEnabled = state.fieldsStatus,
                placeholder = "Contraseña",
                value = password,
                icon = state.passwordIcon,
                visibilityPassword = state.passwordDisplayStatus,
                transformation = state.passwordMask,
                onValueChangeAction = {
                    viewModel.onValueChange(email = email, password = it)
                },
                onVisibleChangeAction = {
                    viewModel.onChangePasswordTransformation(status = it)
                }
            )

            Spacer(modifier = Modifier.size(24.dp))

            DefaultButton(
                modifier = Modifier.fillMaxWidth(),
                title = "INICIAR SESIÓN",
                isEnabled = state.loginButtonStatus,
                fontWeight = FontWeight.Bold,
                onClickAction = {
                    viewModel.onLoginClick()
                }
            )
        }
    }
}

@Composable
fun LoginFooterContent(modifier: Modifier, navController: NavHostController) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "¿Aún no tienes una cuenta?",
            style = MaterialTheme.typography.body2
        )

        Spacer(modifier = Modifier.size(8.dp))

        TextButton(onClick = {
            navController.navigate(route = Register.route)
        }) {
            Text(
                text = "REGÍSTRATE",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

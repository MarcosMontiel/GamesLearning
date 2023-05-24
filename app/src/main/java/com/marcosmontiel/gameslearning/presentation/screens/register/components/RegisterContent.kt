package com.marcosmontiel.gameslearning.presentation.screens.register.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.marcosmontiel.gameslearning.presentation.components.DefaultButton
import com.marcosmontiel.gameslearning.presentation.components.DefaultPasswordTextField
import com.marcosmontiel.gameslearning.presentation.components.DefaultText
import com.marcosmontiel.gameslearning.presentation.components.DefaultTextField
import com.marcosmontiel.gameslearning.presentation.screens.register.RegisterState
import com.marcosmontiel.gameslearning.presentation.screens.register.RegisterViewModel
import com.marcosmontiel.gameslearning.presentation.ui.theme.Blue500
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray500
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray800

@Composable
fun RegisterContent(
    modifier: Modifier,
    viewModel: RegisterViewModel = hiltViewModel(),
    paddingValues: PaddingValues
) {
    val state: RegisterState = viewModel.state

    Box(modifier = modifier.padding(paddingValues = paddingValues)) {

        RegisterHeaderBackground(
            modifier = Modifier.align(Alignment.TopCenter)
        )

        RegisterCardContent(
            modifier = Modifier.align(Alignment.Center),
            state = state,
            viewModel = viewModel,
            background = Gray800
        )

    }
}

@Composable
fun RegisterHeaderBackground(modifier: Modifier, height: Dp = 224.dp) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(Blue500)
    )
}

@Composable
fun RegisterCardContent(
    modifier: Modifier,
    state: RegisterState,
    viewModel: RegisterViewModel,
    background: Color,
) {
    val username by viewModel.username.observeAsState(initial = "")
    val email by viewModel.email.observeAsState(initial = "")
    val password by viewModel.password.observeAsState(initial = "")
    val confirmPass by viewModel.confirmPass.observeAsState(initial = "")

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
                text = "REGISTRO"
            )

            Spacer(modifier = Modifier.size(8.dp))

            DefaultText(
                modifier = Modifier,
                text = "Ingresa los siguientes datos para continuar",
                color = Gray500,
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.size(16.dp))

            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                isEnabled = state.fieldsStatus,
                placeholder = "Nombre de usuario",
                value = username,
                onValueChangeAction = {
                    viewModel.onValueChange(
                        username = it,
                        email = email,
                        password = password,
                        confirmPass = confirmPass
                    )
                }
            )

            Spacer(modifier = Modifier.size(16.dp))

            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                isEnabled = state.fieldsStatus,
                placeholder = "Correo electrónico",
                value = email,
                keyboardType = KeyboardType.Email,
                onValueChangeAction = {
                    viewModel.onValueChange(
                        username = username,
                        email = it,
                        password = password,
                        confirmPass = confirmPass
                    )
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
                    viewModel.onValueChange(
                        username = username,
                        email = email,
                        password = it,
                        confirmPass = confirmPass
                    )
                },
                onVisibleChangeAction = {
                    viewModel.onChangePasswordTransformation(status = it)
                }
            )

            Spacer(modifier = Modifier.size(16.dp))

            DefaultPasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                isEnabled = state.fieldsStatus,
                placeholder = "Confirmar Contraseña",
                value = confirmPass,
                icon = state.confirmPasswordIcon,
                visibilityPassword = state.confirmPasswordDisplayStatus,
                transformation = state.confirmPasswordMask,
                onValueChangeAction = {
                    viewModel.onValueChange(
                        username = username,
                        email = email,
                        password = password,
                        confirmPass = it
                    )
                },
                onVisibleChangeAction = {
                    viewModel.onChangeConfirmPasswordTransformation(status = it)
                }
            )

            Spacer(modifier = Modifier.size(24.dp))

            DefaultButton(
                modifier = Modifier.fillMaxWidth(),
                isEnabled = state.saveButtonStatus,
                title = "Guardar",
                onClickAction = {
                    viewModel.onRegisterUser()
                }
            )

        }

    }
}

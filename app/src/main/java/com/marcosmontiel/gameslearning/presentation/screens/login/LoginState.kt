package com.marcosmontiel.gameslearning.presentation.screens.login

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

data class LoginState(
    val fieldsStatus: Boolean = true,
    val loginButtonStatus: Boolean = false,
    val passwordDisplayStatus: Boolean = false,
    val passwordIcon: ImageVector = Icons.Rounded.Visibility,
    val passwordMask: VisualTransformation = PasswordVisualTransformation(),
)

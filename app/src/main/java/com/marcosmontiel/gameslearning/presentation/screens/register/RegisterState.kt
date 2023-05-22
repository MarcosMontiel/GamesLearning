package com.marcosmontiel.gameslearning.presentation.screens.register

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

data class RegisterState(
    val confirmPasswordDisplayStatus: Boolean = false,
    val confirmPasswordIcon: ImageVector = Icons.Rounded.Visibility,
    val confirmPasswordMask: VisualTransformation = PasswordVisualTransformation(),
    val fieldsStatus: Boolean = true,
    val passwordDisplayStatus: Boolean = false,
    val passwordIcon: ImageVector = Icons.Rounded.Visibility,
    val passwordMask: VisualTransformation = PasswordVisualTransformation(),
    val saveButtonStatus: Boolean = false,
)

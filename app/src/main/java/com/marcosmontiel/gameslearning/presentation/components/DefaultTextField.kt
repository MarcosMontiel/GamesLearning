package com.marcosmontiel.gameslearning.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun DefaultTextField(
    modifier: Modifier,
    isEnabled: Boolean = true,
    placeholder: String,
    value: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChangeAction: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = {
            onValueChangeAction(it)
        },
        modifier = modifier,
        enabled = isEnabled,
        placeholder = {
            Text(
                text = placeholder
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun DefaultPasswordTextField(
    modifier: Modifier,
    isEnabled: Boolean = true,
    placeholder: String,
    value: String,
    icon: ImageVector,
    visibilityPassword: Boolean,
    transformation: VisualTransformation,
    onValueChangeAction: (String) -> Unit,
    onVisibleChangeAction: (Boolean) -> Unit
) {
    TextField(
        value = value,
        onValueChange = { onValueChangeAction(it) },
        modifier = modifier,
        enabled = isEnabled,
        placeholder = {
            Text(
                text = placeholder
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                onVisibleChangeAction(!visibilityPassword)
            }) {
                Icon(
                    imageVector = icon,
                    contentDescription = "password icon"
                )
            }
        },
        visualTransformation = transformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

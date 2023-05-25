package com.marcosmontiel.gameslearning.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.marcosmontiel.gameslearning.presentation.ui.theme.Blue500

@Composable
fun DefaultFloatingActionButton(
    modifier: Modifier = Modifier,
    background: Color = Blue500,
    icon: ImageVector,
    onClickAction: () -> Unit
) {
    FloatingActionButton(
        onClick = {
            onClickAction()
        },
        modifier = modifier.padding(bottom = 56.dp),
        backgroundColor = background,
        content = {

            Icon(
                imageVector = icon,
                contentDescription = "floating icon"
            )

        }
    )
}

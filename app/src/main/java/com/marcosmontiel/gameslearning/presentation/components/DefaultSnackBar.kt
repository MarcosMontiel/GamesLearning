package com.marcosmontiel.gameslearning.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray200
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray900

@Composable
fun DefaultSnackBarHost(
    snackBarState: SnackbarHostState,
    modifier: Modifier,
    showIcon: Boolean = true,
    tint: Color,
    icon: ImageVector,
    message: String
) {
    SnackbarHost(
        hostState = snackBarState,
        modifier = modifier.padding(all = 16.dp),
        snackbar = {

            DefaultSnackBar(
                showIcon = showIcon,
                tint = tint,
                icon = icon,
                message = message
            )

        }
    )
}

@Composable
fun DefaultSnackBar(
    showIcon: Boolean,
    tint: Color,
    icon: ImageVector,
    message: String
) {
    Snackbar(
        backgroundColor = Gray200,
        contentColor = Gray900,
        elevation = 6.dp,
        content = {

            Row(verticalAlignment = Alignment.CenterVertically) {

                if (showIcon) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "snack bar icon",
                        tint = tint
                    )

                    Spacer(modifier = Modifier.size(8.dp))
                }

                Text(
                    text = message,
                    style = MaterialTheme.typography.body2
                )

            }
        }

    )
}

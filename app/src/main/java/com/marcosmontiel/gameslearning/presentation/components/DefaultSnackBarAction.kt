package com.marcosmontiel.gameslearning.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray200
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray900

@Composable
fun DefaultSnackBarActionHost(
    snackBarState: SnackbarHostState,
    modifier: Modifier,
    background: Color = Gray200,
    content: Color = Gray900,
    message: String,
    action: String,
    onClickAction: () -> Unit
) {
    SnackbarHost(
        hostState = snackBarState,
        modifier = modifier.padding(all = 16.dp),
        snackbar = {
            DefaultSnackBar(
                background = background,
                content = content,
                message = message,
                action = action,
                onClickAction = {
                    onClickAction()
                }
            )
        }
    )
}

@Composable
fun DefaultSnackBar(
    background: Color,
    content: Color,
    message: String,
    action: String,
    onClickAction: () -> Unit
) {
    Snackbar(
        action = {

            TextButton(
                onClick = {
                    onClickAction()
                },
                content = {

                    DefaultText(
                        text = action,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.body2
                    )

                }
            )

        },
        backgroundColor = background,
        contentColor = content,
        elevation = 6.dp,
        content = {

            DefaultText(
                text = message,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.body2
            )

        }
    )
}

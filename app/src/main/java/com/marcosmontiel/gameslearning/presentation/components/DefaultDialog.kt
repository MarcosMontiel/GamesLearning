package com.marcosmontiel.gameslearning.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
fun DefaultDialog(
    showDialog: Boolean,
    content: @Composable () -> Unit,
    onDismissAction: () -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = {
                onDismissAction()
            },
            content = {
                content()
            }
        )
    }
}

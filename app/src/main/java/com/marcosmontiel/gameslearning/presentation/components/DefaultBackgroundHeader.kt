package com.marcosmontiel.gameslearning.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.marcosmontiel.gameslearning.presentation.ui.theme.Blue500

@Composable
fun DefaultBackgroundHeader(
    modifier: Modifier = Modifier,
    height: Dp = 280.dp,
    color: Color = Blue500,
    content: @Composable (BoxScope.() -> Unit)
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(color),
        content = {
            content()
        }
    )
}

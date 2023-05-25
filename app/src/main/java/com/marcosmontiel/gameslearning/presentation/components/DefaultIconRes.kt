package com.marcosmontiel.gameslearning.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DefaultIconRes(
    modifier: Modifier = Modifier,
    size: Dp = 98.dp,
    @DrawableRes drawable: Int
) {
    Image(
        painter = painterResource(id = drawable),
        contentDescription = "drawable",
        modifier = modifier.size(size)
    )
}

package com.marcosmontiel.gameslearning.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.marcosmontiel.gameslearning.R
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray900

@Composable
fun DefaultImage(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    fraction: Float = 1f,
    color: Color = Gray900,
    @DrawableRes image: Int = R.drawable.profile
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(color = color),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "profile image",
            modifier = Modifier
                .clip(shape)
                .fillMaxSize(fraction = fraction),
            contentScale = ContentScale.Crop
        )
    }
}

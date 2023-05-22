package com.marcosmontiel.gameslearning.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray900

@Composable
fun DefaultAvatarAsyncImage(
    modifier: Modifier = Modifier,
    size: Dp = 130.dp,
    fraction: Float = 1f,
    color: Color = Gray900,
    image: Any
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color = color),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = image,
            contentDescription = "profile image",
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxSize(fraction = fraction),
            contentScale = ContentScale.Crop
        )
    }
}

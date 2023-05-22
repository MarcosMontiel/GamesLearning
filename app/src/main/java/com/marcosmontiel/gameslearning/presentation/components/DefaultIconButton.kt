package com.marcosmontiel.gameslearning.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray200
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray700
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray800

@Composable
fun DefaultAvatarIconButton(
    modifier: Modifier = Modifier,
    buttonSize: Dp = 48.dp,
    borderWidth: Dp = 0.dp,
    borderColor: Color = Gray700,
    shape: Shape = CircleShape,
    background: Color = Gray800,
    iconSize: Dp = 24.dp,
    iconColor: Color = Gray200,
    isEnabled: Boolean = true,
    icon: ImageVector = Icons.Rounded.PhotoCamera,
    onClickAction: () -> Unit
) {
    IconButton(
        onClick = {
            onClickAction()
        },
        modifier = modifier
            .clip(shape)
            .background(background)
            .border(
                width = borderWidth,
                color = borderColor,
                shape = shape
            )
            .size(buttonSize),
        enabled = isEnabled
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "camera",
            modifier = Modifier.size(iconSize),
            tint = iconColor
        )
    }
}

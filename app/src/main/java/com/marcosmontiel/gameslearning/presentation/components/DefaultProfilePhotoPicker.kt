package com.marcosmontiel.gameslearning.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Collections
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.marcosmontiel.gameslearning.presentation.ui.theme.Blue500
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray800

@Composable
fun DefaultProfilePhotoPicker(
    modifier: Modifier = Modifier,
    background: Color = Gray800,
    onGalleryChoose: () -> Unit,
    onTakePicture: () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = background,
                shape = RoundedCornerShape(size = 8.dp)
            )
            .padding(16.dp)
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {

            Text(
                text = "Foto del perfil",
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.size(24.dp))

            DefaultProfileIconsChooser(
                modifier = Modifier.fillMaxWidth(),
                onGalleryChoose = {
                    onGalleryChoose()
                },
                onTakePicture = {
                    onTakePicture()
                }
            )

        }

    }
}

@Composable
fun DefaultProfileIconsChooser(
    modifier: Modifier,
    onGalleryChoose: () -> Unit,
    onTakePicture: () -> Unit
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        DefaultProfileIcon(
            icon = Icons.Rounded.PhotoCamera,
            title = "Cámara",
            onClickAction = {
                onTakePicture()
            }
        )

        Spacer(modifier = Modifier.weight(weight = 1f))

        DefaultProfileIcon(
            icon = Icons.Rounded.Collections,
            title = "Galería",
            onClickAction = {
                onGalleryChoose()
            }
        )

    }
}

@Composable
fun DefaultProfileIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    onClickAction: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DefaultAvatarIconButton(
            borderWidth = 1.dp,
            iconColor = Blue500,
            icon = icon,
            onClickAction = {
                onClickAction()
            }
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.body2
        )

    }
}

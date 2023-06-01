package com.marcosmontiel.gameslearning.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray500

@Composable
fun DefaultEmptyScreen(
    modifier: Modifier,
    @DrawableRes image: Int,
    title: String,
    subtitle: String
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
        content = {

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                DefaultIconRes(
                    drawable = image,
                    size = 120.dp
                )

                Spacer(modifier = Modifier.size(16.dp))

                DefaultText(
                    text = title,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.size(8.dp))

                DefaultText(
                    text = subtitle,
                    color = Gray500,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.body2
                )

            }

        }
    )
}

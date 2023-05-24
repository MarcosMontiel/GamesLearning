package com.marcosmontiel.gameslearning.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.presentation.ui.theme.Blue700
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray200

@Composable
fun DefaultTopBar(
    title: String,
    upAvailable: Boolean = true,
    navController: NavHostController? = null
) {
    TopAppBar(
        title = {

            DefaultText(
                text = title,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.h6
            )

        },
        navigationIcon = {

            if (upAvailable) {
                IconButton(onClick = {
                    navController?.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back stack",
                        tint = Gray200
                    )
                }
            }

        },
        backgroundColor = Blue700
    )
}

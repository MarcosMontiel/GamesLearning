package com.marcosmontiel.gameslearning.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.presentation.ui.theme.Blue700
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray200

@Composable
fun DefaultTopBar(
    title: String,
    upAvailable: Boolean = false,
    navController: NavHostController? = null
) {
    TopAppBar(
        title = {
            Text(text = title)
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

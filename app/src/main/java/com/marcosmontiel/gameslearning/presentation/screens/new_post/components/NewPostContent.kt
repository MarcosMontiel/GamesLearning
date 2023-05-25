package com.marcosmontiel.gameslearning.presentation.screens.new_post.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.R
import com.marcosmontiel.gameslearning.presentation.components.DefaultBackgroundHeader
import com.marcosmontiel.gameslearning.presentation.components.DefaultIconRes
import com.marcosmontiel.gameslearning.presentation.components.DefaultText
import com.marcosmontiel.gameslearning.presentation.components.DefaultTextField
import com.marcosmontiel.gameslearning.presentation.screens.new_post.NewPostViewModel
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray800

@Composable
fun NewPostContent(
    modifier: Modifier,
    viewModel: NewPostViewModel = hiltViewModel(),
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    Box(modifier = modifier.padding(paddingValues = paddingValues)) {

        DefaultBackgroundHeader(
            modifier = Modifier.align(Alignment.TopCenter),
            content = {

                NewPostHeaderContent()

            }
        )

        NewPostCardContent(
            modifier = Modifier.align(Alignment.Center),
            viewModel = viewModel,
            background = Gray800
        )

    }
}

@Composable
fun NewPostHeaderContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DefaultIconRes(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp),
            size = 68.dp,
            drawable = R.drawable.add_picture
        )

        Spacer(modifier = Modifier.size(16.dp))

        DefaultText(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "SELECCIONA UNA IMAGEN"
        )

    }
}

@Composable
fun NewPostCardContent(
    modifier: Modifier,
    viewModel: NewPostViewModel,
    background: Color
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        backgroundColor = background,
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = modifier.padding(
                horizontal = 24.dp,
                vertical = 56.dp
            )
        ) {

            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                isEnabled = true,
                placeholder = "Nombre del juego",
                value = "",
                keyboardType = KeyboardType.Text,
                onValueChangeAction = {

                }
            )

            Spacer(modifier = Modifier.size(16.dp))

            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                isEnabled = true,
                placeholder = "Descripción",
                value = "",
                keyboardType = KeyboardType.Text,
                onValueChangeAction = {

                }
            )

            Spacer(modifier = Modifier.size(16.dp))

            DefaultText(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),
                text = "CATEGORÍAS"
            )

        }
    }
}

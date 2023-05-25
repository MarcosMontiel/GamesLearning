package com.marcosmontiel.gameslearning.presentation.screens.new_post.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.R
import com.marcosmontiel.gameslearning.presentation.components.DefaultBackgroundHeader
import com.marcosmontiel.gameslearning.presentation.components.DefaultIconRes
import com.marcosmontiel.gameslearning.presentation.components.DefaultText
import com.marcosmontiel.gameslearning.presentation.screens.new_post.NewPostViewModel

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
            size = 70.dp,
            drawable = R.drawable.add_picture
        )

        Spacer(modifier = Modifier.size(16.dp))

        DefaultText(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "SELECCIONA UNA IMAGEN"
        )

    }
}

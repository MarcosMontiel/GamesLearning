package com.marcosmontiel.gameslearning.presentation.screens.new_post.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.presentation.screens.new_post.NewPostViewModel
import com.marcosmontiel.gameslearning.presentation.ui.theme.Blue500

@Composable
fun NewPostContent(
    modifier: Modifier,
    viewModel: NewPostViewModel = hiltViewModel(),
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    Box(modifier = modifier.padding(paddingValues = paddingValues)) {

        NewPostHeaderContent(modifier = Modifier.align(Alignment.TopCenter))

    }
}

@Composable
fun NewPostHeaderContent(modifier: Modifier, height: Dp = 280.dp) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(Blue500)
    ) {

    }
}

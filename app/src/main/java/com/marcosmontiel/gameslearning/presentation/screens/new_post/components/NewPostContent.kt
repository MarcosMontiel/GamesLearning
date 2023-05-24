package com.marcosmontiel.gameslearning.presentation.screens.new_post.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.presentation.screens.new_post.NewPostViewModel

@Composable
fun NewPostContent(
    modifier: Modifier,
    viewModel: NewPostViewModel = hiltViewModel(),
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    Box(modifier = modifier.padding(paddingValues = paddingValues)) {

    }
}

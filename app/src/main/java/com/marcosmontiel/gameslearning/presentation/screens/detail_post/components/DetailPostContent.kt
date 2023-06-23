package com.marcosmontiel.gameslearning.presentation.screens.detail_post.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.presentation.components.DefaultAsyncImage
import com.marcosmontiel.gameslearning.presentation.screens.detail_post.DetailPostViewModel

@Composable
fun DetailPostContent(
    modifier: Modifier,
    viewModel: DetailPostViewModel = hiltViewModel(),
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    Box(modifier = modifier.padding(paddingValues = paddingValues)) {

        Column(modifier = Modifier.fillMaxWidth()) {

            DefaultAsyncImage(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(0.dp),
                image = ""
            )

            Spacer(modifier = Modifier.size(16.dp))

            DetailPostData(modifier = Modifier.fillMaxWidth())

        }

    }
}

@Composable
fun DetailPostData(modifier: Modifier) {
    Column(modifier = modifier) {

        DetailUserCard(modifier = Modifier.fillMaxWidth())

    }
}

@Composable
fun DetailUserCard(modifier: Modifier) {
    Card(modifier = modifier) {

    }
}

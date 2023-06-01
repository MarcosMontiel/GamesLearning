package com.marcosmontiel.gameslearning.presentation.screens.posts.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.domain.model.Post
import com.marcosmontiel.gameslearning.presentation.components.DefaultAsyncImage
import com.marcosmontiel.gameslearning.presentation.screens.posts.PostsViewModel
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray800

@Composable
fun PostsContent(
    modifier: Modifier,
    viewModel: PostsViewModel = hiltViewModel(),
    navController: NavHostController,
    paddingValues: PaddingValues,
    posts: List<Post>
) {
    Box(
        modifier = modifier.padding(paddingValues = paddingValues),
        content = {

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                content = {

                    items(items = posts) { post ->

                        PostCard(
                            modifier = Modifier.fillMaxWidth(),
                            background = Gray800,
                            post = post
                        )

                    }

                }
            )

        }
    )
}

@Composable
fun PostCard(modifier: Modifier, background: Color, post: Post) {
    Card(
        modifier = modifier.padding(20.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = background,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            DefaultAsyncImage(
                modifier = Modifier.size(80.dp),
                image = post.image
            )

        }
    }
}

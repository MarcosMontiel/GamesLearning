package com.marcosmontiel.gameslearning.presentation.screens.posts.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.domain.model.Post
import com.marcosmontiel.gameslearning.presentation.screens.posts.PostsViewModel

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
                            modifier = Modifier,
                            post = post
                        )

                    }

                }
            )

        }
    )
}

@Composable
fun PostCard(modifier: Modifier, post: Post) {
    Text(text = post.name)
}

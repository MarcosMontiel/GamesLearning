package com.marcosmontiel.gameslearning.presentation.screens.posts.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.R
import com.marcosmontiel.gameslearning.domain.model.Post
import com.marcosmontiel.gameslearning.presentation.components.DefaultAsyncImage
import com.marcosmontiel.gameslearning.presentation.components.DefaultAvatarIconButton
import com.marcosmontiel.gameslearning.presentation.components.DefaultEmptyScreen
import com.marcosmontiel.gameslearning.presentation.components.DefaultText
import com.marcosmontiel.gameslearning.presentation.navigation.DetailsRoutes.DetailPost
import com.marcosmontiel.gameslearning.presentation.screens.posts.PostsViewModel
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray500
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray800

@Composable
fun PostsContent(
    modifier: Modifier,
    navController: NavHostController,
    paddingValues: PaddingValues,
    posts: List<Post>,
) {
    Box(
        modifier = modifier.padding(paddingValues = paddingValues),
        content = {

            if (posts.isEmpty()) {

                DefaultEmptyScreen(
                    modifier = Modifier.fillMaxSize(),
                    image = R.drawable.joystick,
                    title = "No hay publicaciones para mostrar",
                    subtitle = "Sé el primero en hacerlo",
                )

            } else {

                PostsRecyclerView(
                    modifier = Modifier.fillMaxWidth(),
                    navController = navController,
                    posts = posts,
                )

            }

        }
    )
}

@Composable
fun PostsRecyclerView(
    modifier: Modifier,
    navController: NavHostController,
    posts: List<Post>
) {
    LazyColumn(
        modifier = modifier,
        content = {

            itemsIndexed(items = posts) { index, post ->

                val size: Int = posts.size - 1
                val paddingTop: Dp = if (index == 0) 30.dp else 10.dp
                val paddingBottom: Dp = if (index == size) 84.dp else 10.dp

                PostCard(
                    modifier = Modifier.padding(
                        top = paddingTop,
                        end = 20.dp,
                        bottom = paddingBottom,
                        start = 20.dp
                    ),
                    navController = navController,
                    background = Gray800,
                    post = post
                )

            }

        }
    )
}

@Composable
fun PostCard(
    modifier: Modifier,
    viewModel: PostsViewModel = hiltViewModel(),
    navController: NavHostController,
    background: Color,
    post: Post
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(DetailPost.createArgs(post = post.toJson()))
            },
        shape = RoundedCornerShape(16.dp),
        backgroundColor = background,
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            DefaultAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(8.dp),
                image = post.image
            )

            Spacer(modifier = Modifier.size(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                DefaultText(text = post.name)

                Row(verticalAlignment = Alignment.CenterVertically) {

                    val hasLike = post.likes.contains(viewModel.currentUserId)
                    val icon = if (hasLike) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder

                    DefaultAvatarIconButton(
                        borderColor = Gray800,
                        icon = icon,
                    ) {

                        viewModel.like(post)

                    }

                    DefaultText(
                        text = post.likes.size.toString(),
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.body2
                    )

                }

            }

            Spacer(modifier = Modifier.size(8.dp))

            DefaultText(
                modifier = Modifier.fillMaxWidth(),
                text = post.description,
                color = Gray500,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.body2,
            )

            Spacer(modifier = Modifier.size(8.dp))

            DefaultText(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End),
                text = post.user?.username ?: "unknown user",
                color = Gray500,
                style = MaterialTheme.typography.body2,
            )

        }
    }
}

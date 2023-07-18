package com.marcosmontiel.gameslearning.presentation.screens.detail_post.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.R
import com.marcosmontiel.gameslearning.domain.model.Post
import com.marcosmontiel.gameslearning.domain.model.User
import com.marcosmontiel.gameslearning.presentation.components.DefaultAsyncImage
import com.marcosmontiel.gameslearning.presentation.components.DefaultIconRes
import com.marcosmontiel.gameslearning.presentation.components.DefaultImage
import com.marcosmontiel.gameslearning.presentation.components.DefaultText
import com.marcosmontiel.gameslearning.presentation.screens.detail_post.DetailPostViewModel
import com.marcosmontiel.gameslearning.presentation.ui.theme.Blue300
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray400
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray500
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray800

@Composable
fun DetailPostContent(
    modifier: Modifier,
    viewModel: DetailPostViewModel = hiltViewModel(),
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    val scrollState: ScrollState = rememberScrollState()
    val post: Post = viewModel.post
    val categoryRes by viewModel.categoryRes.observeAsState(initial = R.drawable.playstation)

    Box(modifier = modifier.padding(paddingValues = paddingValues)) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(state = scrollState)
        ) {

            HeaderPostData(
                modifier = Modifier.fillMaxWidth(),
                navController = navController,
                image = post.image
            )

            Spacer(modifier = Modifier.size(24.dp))

            if (post.user != null) {

                DetailUserCard(
                    modifier = Modifier.fillMaxWidth(),
                    background = Gray800,
                    user = post.user!!,
                )

            }

            DetailsGameData(
                modifier = Modifier.fillMaxWidth(),
                categoryRes = categoryRes,
                post = post
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 24.dp,
                        end = 16.dp,
                        bottom = 16.dp,
                        start = 16.dp
                    )
            )

            DetailsDescGameData(
                modifier = Modifier.fillMaxWidth(),
                post = post
            )

        }

    }
}

@Composable
fun HeaderPostData(
    modifier: Modifier,
    navController: NavHostController,
    image: String
) {
    Box(modifier = modifier.height(220.dp)) {

        DefaultAsyncImage(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(0.dp),
            image = image,
            alpha = 0.6f,
        )

        IconButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.padding(8.dp),
            content = {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "icon back"
                )
            }
        )

    }
}

@Composable
fun DetailUserCard(
    modifier: Modifier,
    background: Color,
    user: User,
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .height(102.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = background,
        elevation = 4.dp
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            if (user.avatar.isEmpty()) {

                DefaultImage(modifier = Modifier.size(70.dp))

            } else {

                DefaultAsyncImage(
                    modifier = Modifier.size(70.dp),
                    image = user.avatar
                )

            }

            Spacer(modifier = Modifier.size(16.dp))

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {

                DefaultText(
                    text = user.username,
                    fontWeight = FontWeight.Normal,
                )

                DefaultText(
                    text = user.email,
                    color = Gray500,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.body2
                )

            }

        }

    }
}

@Composable
fun DetailsGameData(
    modifier: Modifier,
    @DrawableRes categoryRes: Int,
    post: Post
) {
    Column(modifier = modifier.padding(16.dp)) {

        DefaultText(
            text = post.name,
            color = Blue300,
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.size(16.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(32.dp))
                .background(color = Blue300)
                .padding(horizontal = 32.dp, vertical = 8.dp),
            content = {

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    content = {

                        DefaultIconRes(
                            size = 20.dp,
                            drawable = categoryRes,
                        )

                        Spacer(modifier = Modifier.size(8.dp))

                        DefaultText(text = post.category)

                    }
                )

            }
        )

    }
}

@Composable
fun DetailsDescGameData(modifier: Modifier, post: Post) {
    Column(modifier = modifier.padding(16.dp)) {

        DefaultText(
            text = "Descripción",
            color = Gray400,
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.size(16.dp))

        DefaultText(
            text = post.description,
            color = Gray500,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.body2
        )

        Spacer(modifier = Modifier.size(32.dp))

    }
}

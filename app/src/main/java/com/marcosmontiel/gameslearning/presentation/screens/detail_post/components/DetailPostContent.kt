package com.marcosmontiel.gameslearning.presentation.screens.detail_post.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
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

            DefaultAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                shape = RoundedCornerShape(0.dp),
                image = post.image
            )

            Spacer(modifier = Modifier.size(24.dp))

            DetailsPostData(
                modifier = Modifier.fillMaxWidth(),
                post = post
            )

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
fun DetailsPostData(modifier: Modifier, post: Post) {
    Column(modifier = modifier) {

        DetailUserCard(
            modifier = Modifier.fillMaxWidth(),
            post = post,
            background = Gray800
        )

    }
}

@Composable
fun DetailUserCard(
    modifier: Modifier,
    post: Post,
    background: Color
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

            val avatar: String = post.user?.avatar ?: ""

            if (avatar.isNotEmpty()) {

                DefaultAsyncImage(
                    modifier = Modifier.size(70.dp),
                    image = post.user!!.avatar
                )

            } else {

                DefaultImage(modifier = Modifier.size(70.dp))

            }

            Spacer(modifier = Modifier.size(16.dp))

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {

                DefaultText(
                    text = post.user?.username ?: "Unknown",
                    fontWeight = FontWeight.Normal,
                )

                DefaultText(
                    text = post.user?.email ?: "Unknown",
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

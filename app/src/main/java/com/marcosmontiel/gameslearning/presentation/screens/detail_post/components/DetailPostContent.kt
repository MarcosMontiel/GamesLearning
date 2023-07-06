package com.marcosmontiel.gameslearning.presentation.screens.detail_post.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.R
import com.marcosmontiel.gameslearning.presentation.components.DefaultAsyncImage
import com.marcosmontiel.gameslearning.presentation.components.DefaultIconRes
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
    Box(modifier = modifier.padding(paddingValues = paddingValues)) {

        Column(modifier = Modifier.fillMaxWidth()) {

            DefaultAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                shape = RoundedCornerShape(0.dp),
                image = "https://i.blogs.es/4c03cc/halo-4/1366_2000.jpeg"
            )

            Spacer(modifier = Modifier.size(24.dp))

            DetailsPostData(modifier = Modifier.fillMaxWidth())

            DetailsGameData(modifier = Modifier.fillMaxWidth())

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

            DetailsDescGameData(modifier = Modifier.fillMaxWidth())

        }

    }
}

@Composable
fun DetailsPostData(modifier: Modifier) {
    Column(modifier = modifier) {

        DetailUserCard(
            modifier = Modifier.fillMaxWidth(),
            background = Gray800
        )

    }
}

@Composable
fun DetailUserCard(modifier: Modifier, background: Color) {
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

            DefaultAsyncImage(
                modifier = Modifier.size(70.dp),
                image = "https://i.blogs.es/4c03cc/halo-4/1366_2000.jpeg"
            )

            Spacer(modifier = Modifier.size(16.dp))

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {

                DefaultText(
                    text = "Nombre de usuario",
                    fontWeight = FontWeight.Normal,
                )

                DefaultText(
                    text = "Marcos Montiel",
                    color = Gray500,
                    fontWeight = FontWeight.Normal,
                )

            }

        }

    }
}

@Composable
fun DetailsGameData(modifier: Modifier) {
    Column(modifier = modifier.padding(16.dp)) {

        DefaultText(
            text = "Nombre del juego",
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
                            drawable = R.drawable.playstation,
                        )

                        Spacer(modifier = Modifier.size(8.dp))

                        DefaultText(text = "PS4")

                    }
                )

            }
        )

    }
}

@Composable
fun DetailsDescGameData(modifier: Modifier) {
    Column(modifier = modifier.padding(16.dp)) {

        DefaultText(
            text = "Descripción",
            color = Gray400,
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.size(16.dp))

        DefaultText(
            text = "test",
            color = Gray500,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.body2
        )

    }
}

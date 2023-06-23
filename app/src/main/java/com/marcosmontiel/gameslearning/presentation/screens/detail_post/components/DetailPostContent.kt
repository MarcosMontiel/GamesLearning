package com.marcosmontiel.gameslearning.presentation.screens.detail_post.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.presentation.components.DefaultAsyncImage
import com.marcosmontiel.gameslearning.presentation.components.DefaultText
import com.marcosmontiel.gameslearning.presentation.screens.detail_post.DetailPostViewModel
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
                    .height(280.dp),
                shape = RoundedCornerShape(0.dp),
                image = "https://i.blogs.es/4c03cc/halo-4/1366_2000.jpeg"
            )

            Spacer(modifier = Modifier.size(24.dp))

            DetailPostData(modifier = Modifier.fillMaxWidth())

        }

    }
}

@Composable
fun DetailPostData(modifier: Modifier) {
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
        modifier = modifier.padding(16.dp),
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
                modifier = Modifier.fillMaxWidth(),
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

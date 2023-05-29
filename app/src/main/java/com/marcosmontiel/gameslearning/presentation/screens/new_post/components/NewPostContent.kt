package com.marcosmontiel.gameslearning.presentation.screens.new_post.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CloudUpload
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.R
import com.marcosmontiel.gameslearning.presentation.components.*
import com.marcosmontiel.gameslearning.presentation.screens.new_post.Category
import com.marcosmontiel.gameslearning.presentation.screens.new_post.NewPostState
import com.marcosmontiel.gameslearning.presentation.screens.new_post.NewPostViewModel
import com.marcosmontiel.gameslearning.presentation.ui.theme.Blue500
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray800

@Composable
fun NewPostContent(
    modifier: Modifier,
    viewModel: NewPostViewModel = hiltViewModel(),
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    val state: NewPostState = viewModel.state

    Box(modifier = modifier.padding(paddingValues = paddingValues)) {

        DefaultBackgroundHeader(
            modifier = Modifier.align(Alignment.TopCenter),
            content = {}
        )

        NewPostCard(
            modifier = Modifier.align(Alignment.Center),
            state = state,
            viewModel = viewModel,
            background = Gray800
        )

    }
}

@Composable
fun NewPostCard(
    modifier: Modifier,
    state: NewPostState,
    viewModel: NewPostViewModel,
    background: Color
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.9f)
            .padding(24.dp),
        backgroundColor = background,
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {

            NewPostCardContent(
                modifier = Modifier.fillMaxWidth(),
                state = state,
                viewModel = viewModel
            )

            Spacer(modifier = Modifier.size(16.dp))

            DefaultButton(
                modifier = Modifier.fillMaxWidth(),
                isEnabled = state.publishButtonStatus,
                title = "Publicar",
                onClickAction = {}
            )

        }
    }
}

@Composable
fun NewPostCardContent(modifier: Modifier, state: NewPostState, viewModel: NewPostViewModel) {
    val scrollState: ScrollState = rememberScrollState()

    val name by viewModel.name.observeAsState(initial = "")
    val description by viewModel.description.observeAsState(initial = "")
    val category by viewModel.category.observeAsState(initial = "")

    Box(modifier = modifier.fillMaxHeight(fraction = 0.85f)) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {

            PicturePostContent(
                modifier = Modifier.fillMaxWidth(),
                state = state,
                viewModel = viewModel
            )

            DefaultTextField(modifier = Modifier.fillMaxWidth(),
                isEnabled = state.fieldsStatus,
                placeholder = "Nombre del juego",
                value = name,
                keyboardType = KeyboardType.Text,
                onValueChangeAction = {

                    viewModel.onValueChange(
                        name = it,
                        description = description
                    )

                }
            )

            Spacer(modifier = Modifier.size(16.dp))

            DefaultTextField(modifier = Modifier.fillMaxWidth(),
                isEnabled = state.fieldsStatus,
                placeholder = "Descripción",
                value = description,
                keyboardType = KeyboardType.Text,
                onValueChangeAction = {

                    viewModel.onValueChange(
                        name = name,
                        description = it
                    )

                }
            )

            Spacer(modifier = Modifier.size(16.dp))

            DefaultText(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),
                text = "CATEGORÍAS"
            )

            Spacer(modifier = Modifier.size(16.dp))

            CategoryOptions(
                viewModel = viewModel,
                categoryName = category
            )

        }

    }
}

@Composable
fun PicturePostContent(
    modifier: Modifier,
    state: NewPostState,
    viewModel: NewPostViewModel,
    height: Dp = 180.dp
) {
    val image by viewModel.image.observeAsState(initial = "")

    Box(
        modifier = modifier
            .height(height)
            .padding(
                top = 0.dp,
                end = 10.dp,
                bottom = 20.dp,
                start = 10.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (picture, icon) = createRefs()

            if (image.isNotEmpty()) {

                DefaultAsyncImage(
                    modifier = Modifier
                        .constrainAs(picture) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                        },
                    shape = RoundedCornerShape(7.dp),
                    fraction = 0.75f,
                    image = image
                )

            } else {

                DefaultImage(
                    modifier = Modifier
                        .constrainAs(picture) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                        },
                    shape = RoundedCornerShape(7.dp),
                    fraction = 0.75f,
                    image = R.drawable.placeholder
                )

            }

            DefaultAvatarIconButton(
                modifier = Modifier.constrainAs(icon) {
                    top.linkTo(picture.bottom, (-20).dp)
                    start.linkTo(picture.end, (-20).dp)
                },
                buttonSize = 40.dp,
                borderWidth = 1.dp,
                borderColor = Blue500,
                background = Blue500,
                iconSize = 20.dp,
                isEnabled = state.photoButtonStatus,
                icon = Icons.Rounded.CloudUpload,
                onClickAction = {

                }
            )

        }
    }
}

@Composable
fun CategoryOptions(
    modifier: Modifier = Modifier,
    viewModel: NewPostViewModel,
    categoryName: String
) {
    val categories = listOf(
        Category(name = "PC", icon = R.drawable.computer),
        Category(name = "PS4", icon = R.drawable.playstation),
        Category(name = "XBOX", icon = R.drawable.xbox),
        Category(name = "NINTENDO", icon = R.drawable.nintendo),
        Category(name = "MOBILE", icon = R.drawable.smartphone),
    )

    categories.forEach { category ->

        DefaultRadioButton(
            modifier = modifier.fillMaxWidth(),
            item = category,
            isSelected = category.name == categoryName,
            onValueChangeAction = {

                viewModel.onCheckedChange(it)

            }
        )

    }
}

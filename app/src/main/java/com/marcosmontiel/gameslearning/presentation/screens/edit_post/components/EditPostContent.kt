package com.marcosmontiel.gameslearning.presentation.screens.edit_post.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CloudUpload
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.marcosmontiel.gameslearning.R
import com.marcosmontiel.gameslearning.domain.model.Category
import com.marcosmontiel.gameslearning.presentation.components.*
import com.marcosmontiel.gameslearning.presentation.screens.edit_post.EditPostState
import com.marcosmontiel.gameslearning.presentation.screens.edit_post.EditPostViewModel
import com.marcosmontiel.gameslearning.presentation.ui.theme.Blue500
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray800
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
@ExperimentalCoroutinesApi
fun EditPostContent(
    modifier: Modifier,
    viewModel: EditPostViewModel = hiltViewModel(),
    paddingValues: PaddingValues
) {
    viewModel.activityHandler.Handle()

    val state: EditPostState = viewModel.state

    Box(modifier = modifier.padding(paddingValues = paddingValues)) {

        DefaultBackgroundHeader(
            modifier = Modifier.align(Alignment.TopCenter),
            content = {},
        )

        EditPostCard(
            modifier = Modifier.align(Alignment.Center),
            viewModel = viewModel,
            state = state,
        )

    }
}

@Composable
@ExperimentalCoroutinesApi
fun EditPostCard(
    modifier: Modifier,
    viewModel: EditPostViewModel,
    state: EditPostState,
    background: Color = Gray800
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

            EditPostCardContent(
                modifier = Modifier.fillMaxWidth(),
                viewModel = viewModel,
                state = state
            )

            Spacer(modifier = Modifier.size(16.dp))

            DefaultButton(
                modifier = Modifier.fillMaxWidth(),
                isEnabled = state.publishButtonStatus,
                title = "Publicar",
            ) {

                viewModel.updatePost()

            }

        }

    }
}

@Composable
@ExperimentalCoroutinesApi
fun EditPostCardContent(
    modifier: Modifier,
    viewModel: EditPostViewModel,
    state: EditPostState
) {
    val scrollState: ScrollState = rememberScrollState()

    Box(modifier = modifier.fillMaxHeight(fraction = 0.85f)) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(state = scrollState)
        ) {

            GalleryChooser(
                modifier = Modifier.fillMaxWidth(),
                viewModel = viewModel,
                state = state,
            )

            Spacer(modifier = Modifier.size(16.dp))

            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                isEnabled = state.fieldsStatus,
                placeholder = "Nombre del juego",
                value = state.name,
                onValueChangeAction = { name ->

                    viewModel.valueChange(
                        name = name,
                        description = state.description,
                    )

                },
            )

            Spacer(modifier = Modifier.size(16.dp))

            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                isEnabled = state.fieldsStatus,
                placeholder = "Descripción",
                value = state.description,
                onValueChangeAction = { desc ->

                    viewModel.valueChange(
                        name = state.name,
                        description = desc,
                    )

                },
            )

            Spacer(modifier = Modifier.size(16.dp))

            DefaultText(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),
                text = "CATEGORÍAS",
            )

            Spacer(modifier = Modifier.size(16.dp))

            CategoryOptions(
                viewModel = viewModel,
                state = state,
            )

        }

    }
}

@Composable
@ExperimentalCoroutinesApi
fun GalleryChooser(
    modifier: Modifier,
    viewModel: EditPostViewModel,
    state: EditPostState,
    height: Dp = 180.dp
) {
    Box(
        modifier = modifier.height(height),
        contentAlignment = Alignment.Center,
    ) {

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {

            val (picture, icon) = createRefs()

            if (state.image.isNotEmpty()) {

                DefaultAsyncImage(
                    modifier = Modifier.constrainAs(picture) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    },
                    shape = RoundedCornerShape(7.dp),
                    fraction = 0.75f,
                    image = state.image,
                )

            } else {

                DefaultImage(
                    modifier = Modifier.constrainAs(icon) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    },
                    shape = RoundedCornerShape(7.dp),
                    fraction = 0.75f,
                    image = R.drawable.placeholder,
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
            ) {

                viewModel.galleryChoose()

            }

        }

    }
}

@Composable
@ExperimentalCoroutinesApi
fun CategoryOptions(
    modifier: Modifier = Modifier,
    viewModel: EditPostViewModel,
    state: EditPostState
) {

    val categories = Category.getCategories()

    categories.forEach { category ->

        DefaultRadioButton(
            modifier = modifier.fillMaxWidth(),
            item = category,
            isSelected = category.name == state.category,
        ) {

            viewModel.checkedChange(it)

        }

    }

}

package com.marcosmontiel.gameslearning.presentation.screens.new_post.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.marcosmontiel.gameslearning.R
import com.marcosmontiel.gameslearning.presentation.components.*
import com.marcosmontiel.gameslearning.presentation.screens.new_post.Category
import com.marcosmontiel.gameslearning.presentation.screens.new_post.NewPostState
import com.marcosmontiel.gameslearning.presentation.screens.new_post.NewPostViewModel
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
            content = {})

        NewPostCardContent(
            modifier = Modifier.align(Alignment.Center),
            state = state,
            viewModel = viewModel,
            background = Gray800
        )

    }
}

@Composable
fun NewPostCardContent(
    modifier: Modifier,
    state: NewPostState,
    viewModel: NewPostViewModel,
    background: Color
) {
    val name by viewModel.name.observeAsState(initial = "")
    val description by viewModel.description.observeAsState(initial = "")
    val category by viewModel.category.observeAsState(initial = "")

    val categories = listOf(
        Category(name = "PC", icon = R.drawable.computer),
        Category(name = "PS4", icon = R.drawable.playstation),
        Category(name = "XBOX", icon = R.drawable.xbox),
        Category(name = "NINTENDO", icon = R.drawable.nintendo),
        Category(name = "MOBILE", icon = R.drawable.smartphone),
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        backgroundColor = background,
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = modifier.padding(
                horizontal = 24.dp, vertical = 56.dp
            )
        ) {

            PicturePostContent(modifier = Modifier.fillMaxWidth())

            DefaultTextField(modifier = Modifier.fillMaxWidth(),
                isEnabled = state.fieldsStatus,
                placeholder = "Nombre del juego",
                value = name,
                keyboardType = KeyboardType.Text,
                onValueChangeAction = {

                    viewModel.onValueChange(
                        name = it,
                        description = description,
                        category = category
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
                        description = it,
                        category = category
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
                modifier = Modifier.fillMaxWidth(),
                categories = categories,
                categoryName = category,
                onValueChangeAction = {

                    viewModel.onValueChange(
                        name = name,
                        description = description,
                        category = it
                    )

                }
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
fun PicturePostContent(modifier: Modifier) {
    Box(modifier = modifier) {

    }
}

@Composable
fun CategoryOptions(
    modifier: Modifier,
    categories: List<Category>,
    categoryName: String,
    onValueChangeAction: (String) -> Unit
) {
    val scrollState = rememberScrollState()

    Box(modifier = modifier.height(170.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {

            categories.forEach { category ->

                DefaultRadioButton(
                    modifier = Modifier.fillMaxWidth(),
                    item = category,
                    isSelected = category.name == categoryName,
                    onValueChangeAction = {
                        onValueChangeAction(it)
                    }
                )

            }

        }
    }
}

package com.marcosmontiel.gameslearning.presentation.screens.profile_update.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.marcosmontiel.gameslearning.presentation.components.*
import com.marcosmontiel.gameslearning.presentation.screens.login.components.GenericLoginTitles
import com.marcosmontiel.gameslearning.presentation.screens.profile_update.ProfileEditState
import com.marcosmontiel.gameslearning.presentation.screens.profile_update.ProfileEditViewModel
import com.marcosmontiel.gameslearning.presentation.ui.theme.Blue500
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray500
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray800
import com.marcosmontiel.gameslearning.presentation.ui.theme.Gray900
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
@ExperimentalCoroutinesApi
fun ProfileEditContent(
    modifier: Modifier,
    viewModel: ProfileEditViewModel = hiltViewModel(),
    paddingValues: PaddingValues
) {
    viewModel.activityHandler.Handle()

    val state: ProfileEditState = viewModel.state

    Box(modifier = modifier.padding(paddingValues = paddingValues)) {

        DefaultDialog(
            showDialog = state.photoSelectorDialogStatus,
            content = {

                DefaultProfilePhotoPicker(
                    modifier = Modifier.fillMaxWidth(),
                    onGalleryChoose = {
                        viewModel.onGalleryChoose()
                        viewModel.onDismissAvatarDialog()
                    },
                    onTakePicture = {
                        viewModel.onTakePicture()
                        viewModel.onDismissAvatarDialog()
                    }
                )

            },
            onDismissAction = {
                viewModel.onDismissAvatarDialog()
            }
        )

        ProfileHeaderContent(
            modifier = Modifier.align(Alignment.TopCenter),
            state = state,
            viewModel = viewModel
        )

        ProfileCardContent(
            modifier = Modifier.align(Alignment.Center),
            state = state,
            viewModel = viewModel,
            background = Gray800
        )

    }
}

@Composable
@ExperimentalCoroutinesApi
fun ProfileHeaderContent(
    modifier: Modifier,
    state: ProfileEditState,
    viewModel: ProfileEditViewModel,
    height: Dp = 210.dp,
) {
    val image by viewModel.image.observeAsState(initial = "")

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (picture, icon) = createRefs()

            if (image.isNotEmpty()) {

                DefaultAvatarAsyncImage(
                    modifier = Modifier.constrainAs(picture) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    },
                    color = Gray900,
                    image = image
                )

            } else {

                DefaultAvatarImage(
                    modifier = Modifier.constrainAs(picture) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    },
                    color = Gray900
                )

            }

            DefaultAvatarIconButton(
                modifier = Modifier.constrainAs(icon) {
                    top.linkTo(picture.bottom, (-40).dp)
                    start.linkTo(picture.end, (-40).dp)
                },
                buttonSize = 40.dp,
                borderWidth = 1.dp,
                borderColor = Blue500,
                background = Blue500,
                iconSize = 20.dp,
                isEnabled = state.photoButtonStatus,
                onClickAction = {
                    viewModel.onShowAvatarDialog()
                }
            )

        }

    }
}

@Composable
@ExperimentalCoroutinesApi
fun ProfileCardContent(
    modifier: Modifier,
    state: ProfileEditState,
    viewModel: ProfileEditViewModel,
    background: Color,
) {
    val username by viewModel.username.observeAsState(initial = "")

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        backgroundColor = background,
        shape = RoundedCornerShape(15.dp)
    ) {

        Column(
            modifier = modifier.padding(
                horizontal = 24.dp,
                vertical = 56.dp
            )
        ) {

            GenericLoginTitles(
                modifier = Modifier,
                title = "ACTUALIZACIÓN",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.size(8.dp))

            GenericLoginTitles(
                modifier = Modifier,
                title = "Ingresa los siguientes datos para continuar",
                color = Gray500,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.size(16.dp))

            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                isEnabled = state.fieldsStatus,
                placeholder = "Nombre de usuario",
                value = username,
                onValueChangeAction = {
                    viewModel.onValueChange(username = it)
                }
            )

            Spacer(modifier = Modifier.size(24.dp))

            DefaultButton(
                modifier = Modifier.fillMaxWidth(),
                title = "ACTUALIZAR DATOS",
                isEnabled = state.updateButtonStatus,
                fontWeight = FontWeight.Bold,
                onClickAction = {
                    viewModel.onSavePhoto()
                }
            )

        }

    }
}

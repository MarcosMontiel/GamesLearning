package com.marcosmontiel.gameslearning.presentation.screens.edit_post

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcosmontiel.gameslearning.domain.model.Post
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.usecase.post.PostUseCases
import com.marcosmontiel.gameslearning.presentation.utils.ComposeFileProvider
import com.marcosmontiel.gameslearning.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class EditPostViewModel @Inject constructor(
    private val application: Application,
    private val postUseCases: PostUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    // Get params

    private val _param: String = savedStateHandle.get<String>("post") ?: ""
    val post: Post = Post.fromJson(_param)

    // Late init variables

    private lateinit var _file: File

    // Instances

    val activityHandler = ResultingActivityHandler()

    // State form

    var state: EditPostState by mutableStateOf(EditPostState())
        private set

    // Response

    var postResponse by mutableStateOf<Response<Boolean>?>(value = null)

    init {
        state = state.copy(
            name = post.name,
            description = post.description,
            category = post.category,
            image = post.image,
        )

        state = state.copy(publishButtonStatus = areValidFields())
    }

    // Events

    fun valueChange(name: String, description: String) {
        state = state.copy(
            name = name,
            description = description,
        )

        state = state.copy(publishButtonStatus = areValidFields())
    }

    fun checkedChange(category: String) {
        state = state.copy(
            category = category,
        )

        state = state.copy(publishButtonStatus = areValidFields())
    }

    fun galleryChoose() = viewModelScope.launch {
        val result: Uri = activityHandler.getContent() ?: return@launch
        val file: File = ComposeFileProvider.createFileFromUri(
            context = application.applicationContext,
            uri = result
        ) ?: return@launch

        state = state.copy(image = file.path)
        _file = file
    }

    fun updatePost() {
        val data = Post(
            category = state.category,
            description = state.description,
            id = post.id,
            image = post.image,
            imageName = post.imageName,
            name = state.name,
        )

        updateData(post = data)
    }

    // Functions

    fun enableFields() {
        state = state.copy(
            fieldsStatus = true,
            photoButtonStatus = true,
            publishButtonStatus = true,
        )

        postResponse = null
    }

    // Private functions

    private fun disableFields() {
        state = state.copy(
            fieldsStatus = false,
            photoButtonStatus = false,
            publishButtonStatus = false,
        )
    }

    private fun updateData(post: Post) = viewModelScope.launch {

        disableFields()

        postResponse = Response.Loading

        postResponse = if (::_file.isInitialized) {

            postUseCases.update(post = post, file = _file)

        } else {

            postUseCases.update(post = post, file = null)

        }

    }

    private fun areValidFields(): Boolean =
        state.name.isNotEmpty() && state.description.isNotEmpty() && state.category.isNotEmpty()

}

package com.marcosmontiel.gameslearning.presentation.screens.new_post

import android.app.Application
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcosmontiel.gameslearning.domain.model.Post
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.usecase.auth.AuthUseCases
import com.marcosmontiel.gameslearning.domain.usecase.post.PostUseCases
import com.marcosmontiel.gameslearning.presentation.utils.ComposeFileProvider
import com.marcosmontiel.gameslearning.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class NewPostViewModel @Inject constructor(
    authUseCases: AuthUseCases,
    private val postUseCases: PostUseCases,
    private val application: Application
) : ViewModel() {

    // Late init variables

    private lateinit var _postData: Post
    private lateinit var _postFile: File

    // Instances

    val activityHandler = ResultingActivityHandler()
    private val _currentUserId: String = authUseCases.getCurrentUser()?.uid ?: ""

    // State form

    var state: NewPostState by mutableStateOf(NewPostState())
        private set

    // LiveData

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _category = MutableLiveData<String>()
    val category: LiveData<String> = _category

    private val _image = MutableLiveData<String>()
    val image: LiveData<String> = _image

    // Response

    private val _postResponse = MutableStateFlow<Response<Boolean>?>(value = null)
    val postResponse: StateFlow<Response<Boolean>?> = _postResponse

    // Events

    fun onValueChange(name: String, description: String) {
        _name.value = name
        _description.value = description

        state = state.copy(
            publishButtonStatus = validateFields(
                name = name,
                description = description,
                category = _category.value ?: "",
            )
        )
    }

    fun onCheckedChange(category: String) {
        _category.value = category

        state = state.copy(
            publishButtonStatus = validateFields(
                name = _name.value ?: "",
                description = _description.value ?: "",
                category = category
            )
        )
    }

    fun onGalleryChoose() = viewModelScope.launch {
        val result: Uri = activityHandler.getContent() ?: return@launch
        val file: File = ComposeFileProvider.createFileFromUri(
            context = application.applicationContext,
            uri = result
        ) ?: return@launch

        _image.value = file.path
        _postFile = file
    }

    fun onCreatePost() {
        _postData = Post(
            category = _category.value!!,
            description = _description.value!!,
            idUser = _currentUserId,
            name = _name.value!!
        )

        createPost()
    }

    // Functions

    fun enableFields() {
        state = state.copy(
            fieldsStatus = true,
            photoButtonStatus = true,
            publishButtonStatus = true,
        )

        _postResponse.value = null
    }

    // Private functions

    private fun disableFields() {
        state = state.copy(
            fieldsStatus = false,
            photoButtonStatus = false,
            publishButtonStatus = false,
        )
    }

    private fun createPost() = viewModelScope.launch {
        if (validateRequiredFields()) {

            disableFields()

            _postResponse.value = Response.Loading
            val result = postUseCases.create(post = _postData, file = _postFile)
            _postResponse.value = result

        }
    }

    private fun validateFields(name: String, description: String, category: String): Boolean =
        name.isNotEmpty() && description.isNotEmpty() && category.isNotEmpty()

    private fun validateRequiredFields(): Boolean {
        if (_image.value.isNullOrEmpty()) {

            Toast.makeText(
                application.applicationContext,
                "You must choose an image to continue.",
                Toast.LENGTH_LONG
            ).show()

            return false

        }

        return true
    }

}

data class Category(
    val name: String,
    val icon: Int,
)

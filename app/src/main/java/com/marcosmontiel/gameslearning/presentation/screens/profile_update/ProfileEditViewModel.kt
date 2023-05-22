package com.marcosmontiel.gameslearning.presentation.screens.profile_update

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.model.User
import com.marcosmontiel.gameslearning.domain.usecase.profile.ProfileUseCases
import com.marcosmontiel.gameslearning.presentation.utils.ComposeFileProvider
import com.marcosmontiel.gameslearning.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class ProfileEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCases: ProfileUseCases,
    @ApplicationContext private val context: Context
) : ViewModel() {

    // Late init variables

    private lateinit var _userData: User
    private lateinit var _profilePicture: File

    // Instances

    val activityHandler = ResultingActivityHandler()

    // Get params

    private val profileParam: String? = savedStateHandle.get<String>("profile")
    val profile: User = User.fromJson(profileParam!!)

    // State form

    var state by mutableStateOf(ProfileEditState())
        private set

    // LiveData

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _image = MutableLiveData<String>()
    val image: LiveData<String> = _image

    // Profile response

    private val _profileResponse = MutableStateFlow<Response<Boolean>?>(value = null)
    val profileResponse: StateFlow<Response<Boolean>?> = _profileResponse

    private val _imageResponse = MutableStateFlow<Response<String>?>(value = null)
    val imageResponse: StateFlow<Response<String>?> = _imageResponse

    init {
        onValueChange(username = profile.username)
        _image.value = profile.avatar
    }

    // Events

    fun onDismissAvatarDialog() {
        state = state.copy(
            photoSelectorDialogStatus = false
        )
    }

    fun onShowAvatarDialog() {
        state = state.copy(
            photoSelectorDialogStatus = true
        )
    }

    fun onValueChange(username: String) {
        _username.value = username
        state = state.copy(
            updateButtonStatus = validateFields(username = username)
        )
    }

    fun onUpdateProfile(imageUrl: String = "") {
        _userData = User(
            id = profile.id,
            username = _username.value!!,
            avatar = imageUrl
        )

        updateProfile()
    }

    fun onGalleryChoose() = viewModelScope.launch {
        val result: Uri = activityHandler.getContent() ?: return@launch
        val file = ComposeFileProvider.createFileFromUri(
            context = context,
            uri = result
        ) ?: return@launch

        _image.value = result.toString()
        _profilePicture = file
    }

    fun onTakePicture() = viewModelScope.launch {
        val result: Bitmap = activityHandler.takePicturePreview() ?: return@launch
        val image = ComposeFileProvider.getPathFromBitmap(context = context, bitmap = result)

        _image.value = image

        if (image == "") {
            return@launch
        }

        _profilePicture = File(image)
    }

    fun onSavePhoto() = viewModelScope.launch {
        disableFields()

        if (::_profilePicture.isInitialized) {

            _imageResponse.value = Response.Loading
            val response = useCases.savePhoto(file = _profilePicture)
            _imageResponse.value = response

        } else {

            _imageResponse.value = Response.Success(data = "")

        }
    }

    // Functions

    fun enableFields() {
        state = state.copy(
            fieldsStatus = true,
            photoButtonStatus = true,
            updateButtonStatus = true,
        )

        _imageResponse.value = null
    }

    // Private functions

    private fun disableFields() {
        state = state.copy(
            fieldsStatus = false,
            photoButtonStatus = false,
            updateButtonStatus = false
        )
    }

    private fun updateProfile() = viewModelScope.launch {
        _profileResponse.value = Response.Loading

        val response = useCases.update(user = _userData)

        _profileResponse.value = response
    }

    private fun validateFields(username: String): Boolean =
        username.length > 8

}

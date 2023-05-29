package com.marcosmontiel.gameslearning.presentation.screens.new_post

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcosmontiel.gameslearning.presentation.utils.ComposeFileProvider
import com.marcosmontiel.gameslearning.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class NewPostViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    // Instances

    val activityHandler = ResultingActivityHandler()

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
        val image: File = ComposeFileProvider.createFileFromUri(
            context = context,
            uri = result
        ) ?: return@launch
    }

    // Private functions

    private fun validateFields(name: String, description: String, category: String): Boolean =
        name.isNotEmpty() && description.isNotEmpty() && category.isNotEmpty()

}

data class Category(
    val name: String,
    val icon: Int,
)

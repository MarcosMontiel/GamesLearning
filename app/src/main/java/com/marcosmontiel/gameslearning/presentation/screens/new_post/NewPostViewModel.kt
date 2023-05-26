package com.marcosmontiel.gameslearning.presentation.screens.new_post

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewPostViewModel @Inject constructor() : ViewModel() {

    // State form

    var state: NewPostState by mutableStateOf(NewPostState())
        private set

    // LiveData

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    // Events

    fun onValueChange(name: String, description: String) {
        _name.value = name
        _description.value = description

        state = state.copy(
            publishButtonStatus = validateFields(name = name, description = description)
        )
    }

    // Private functions

    private fun validateFields(name: String, description: String): Boolean =
        name.isNotEmpty() && description.isNotEmpty()

}

package com.marcosmontiel.gameslearning.presentation.screens.new_post

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewPostViewModel @Inject constructor() : ViewModel() {

    // State form

    var state: NewPostState by mutableStateOf(NewPostState())
        private set

}

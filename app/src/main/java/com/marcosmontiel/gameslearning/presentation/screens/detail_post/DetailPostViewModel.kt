package com.marcosmontiel.gameslearning.presentation.screens.detail_post

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.marcosmontiel.gameslearning.domain.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    // Get params

    private val _postParam: String = savedStateHandle.get<String>("post") ?: ""
    val post: Post = Post.fromJson(_postParam)

}

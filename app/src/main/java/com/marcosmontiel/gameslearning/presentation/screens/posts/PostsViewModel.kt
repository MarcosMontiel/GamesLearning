package com.marcosmontiel.gameslearning.presentation.screens.posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcosmontiel.gameslearning.domain.model.Post
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.usecase.post.PostUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postUseCases: PostUseCases
) : ViewModel() {

    // Response

    var postsResponse by mutableStateOf<Response<List<Post>>?>(null)
    var dislikeResponse by mutableStateOf<Response<Boolean>?>(null)
    var likeResponse by mutableStateOf<Response<Boolean>?>(null)

    init {
        getAllPosts()
    }

    // Events

    fun deleteLike(postId: String, userId: String) = viewModelScope.launch {
        dislikeResponse = Response.Loading
        val response = postUseCases.deleteLike(postId = postId, userId = userId)
        dislikeResponse = response
    }

    fun like(postId: String, userId: String) = viewModelScope.launch {
        likeResponse = Response.Loading
        val response = postUseCases.like(postId = postId, userId = userId)
        likeResponse = response
    }

    // Private functions

    private fun getAllPosts() = viewModelScope.launch {
        postsResponse = Response.Loading
        postUseCases.getPosts().collect { response ->
            postsResponse = response
        }
    }

}

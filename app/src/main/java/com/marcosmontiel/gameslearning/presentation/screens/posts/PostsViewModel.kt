package com.marcosmontiel.gameslearning.presentation.screens.posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcosmontiel.gameslearning.domain.model.Post
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.usecase.auth.AuthUseCases
import com.marcosmontiel.gameslearning.domain.usecase.post.PostUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val postUseCases: PostUseCases,
) : ViewModel() {

    // Current user data

    private val _currentUserId = authUseCases.getCurrentUser()?.uid ?: ""

    // Response

    var postsResponse by mutableStateOf<Response<List<Post>>?>(null)
    var dislikeResponse by mutableStateOf<Response<Boolean>?>(null)
    var likeResponse by mutableStateOf<Response<Boolean>?>(null)

    init {
        getAllPosts()
    }

    // Events

    fun like(post: Post) {

        if (post.likes.contains(_currentUserId)) {

            deleteLike(post.id)

        } else {

            addLike(post.id)

        }

    }

    // Private functions

    private fun deleteLike(postId: String) = viewModelScope.launch {
        dislikeResponse = Response.Loading
        val response = postUseCases.deleteLike(postId = postId, userId = _currentUserId)
        dislikeResponse = response
    }

    private fun addLike(postId: String) = viewModelScope.launch {
        likeResponse = Response.Loading
        val response = postUseCases.like(postId = postId, userId = _currentUserId)
        likeResponse = response
    }

    private fun getAllPosts() = viewModelScope.launch {
        postsResponse = Response.Loading
        postUseCases.getPosts().collect { response ->
            postsResponse = response
        }
    }

}

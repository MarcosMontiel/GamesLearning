package com.marcosmontiel.gameslearning.presentation.screens.my_posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.marcosmontiel.gameslearning.domain.model.Post
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.usecase.auth.AuthUseCases
import com.marcosmontiel.gameslearning.domain.usecase.post.PostUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPostsViewModel @Inject constructor(
    authUseCases: AuthUseCases,
    private val postUseCases: PostUseCases,
) : ViewModel() {

    // Current user data

    private val _currentUser: FirebaseUser? = authUseCases.getCurrentUser()

    // Response

    var postsResponse by mutableStateOf<Response<List<Post>>?>(null)
    var deleteResponse by mutableStateOf<Response<Boolean>?>(null)

    init {
        getMyPosts()
    }

    // Functions

    fun deletePost(id: String) = viewModelScope.launch {
        deleteResponse = Response.Loading
        val response = postUseCases.delete(postId = id)
        deleteResponse = response
    }

    // Private functions

    private fun getMyPosts() = viewModelScope.launch {
        postsResponse = Response.Loading
        postUseCases.getPostsByUser(userId = _currentUser?.uid ?: "").collect { response ->
            postsResponse = response
        }
    }

}

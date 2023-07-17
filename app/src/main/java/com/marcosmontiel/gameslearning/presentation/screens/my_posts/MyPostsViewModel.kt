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
    private val authUseCases: AuthUseCases,
    private val postUseCases: PostUseCases,
) : ViewModel() {

    // Current user data

    private val _currentUser: FirebaseUser = authUseCases.getCurrentUser()!!

    // Response

    var postsResponse by mutableStateOf<Response<List<Post>>?>(null)

    init {
        getMyPosts()
    }

    // Private functions

    private fun getMyPosts() = viewModelScope.launch {
        postsResponse = Response.Loading
        postUseCases.getPostsByUser(_currentUser.uid).collect { response ->
            postsResponse = response
        }
    }

}

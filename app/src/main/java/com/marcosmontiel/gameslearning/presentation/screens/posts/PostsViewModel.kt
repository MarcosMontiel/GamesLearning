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
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postUseCases: PostUseCases
) : ViewModel() {

    // Response

    var postsResponse by mutableStateOf<Response<List<Post>>?>(null)

    init {
        getAllPosts()
    }

    // Functions

    fun convertPostToJson(post: Post): String {

        val userArgument = post.user?.let { data ->
            if (data.avatar.isNotEmpty()) {
                val encoded = URLEncoder.encode(data.avatar, StandardCharsets.UTF_8.toString())
                data.copy(avatar = encoded)
            } else {
                data
            }
        }

        val postArgument = post.let { data ->

            val descEncoded = URLEncoder.encode(data.description, StandardCharsets.UTF_8.toString())

            if (data.image.isNotEmpty()) {
                val encoded = URLEncoder.encode(data.image, StandardCharsets.UTF_8.toString())
                data.copy(image = encoded, user = userArgument, description = descEncoded)
            } else {
                data.copy(user = userArgument, description = descEncoded)
            }

        }

        return postArgument.toJson()

    }

    // Private functions

    private fun getAllPosts() = viewModelScope.launch {
        postsResponse = Response.Loading
        postUseCases.getPosts().collect { response ->
            postsResponse = response
        }
    }

}

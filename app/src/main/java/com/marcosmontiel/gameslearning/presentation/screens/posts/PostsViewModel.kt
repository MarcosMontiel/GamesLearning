package com.marcosmontiel.gameslearning.presentation.screens.posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcosmontiel.gameslearning.domain.model.Post
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.model.User
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

        val encodedUser: User = post.user?.let { data ->
            data.copy(
                avatar = imageEncoder(data.avatar),
                email = stringEncoder(data.email),
                username = stringEncoder(data.username),
            )
        } ?: User()

        val encodedPost: Post = post.let { data ->
            data.copy(
                description = stringEncoder(data.description),
                image = imageEncoder(data.image),
                name = stringEncoder(data.name),
                user = encodedUser,
            )
        }

        return encodedPost.toJson()

    }

    // Private functions

    private fun getAllPosts() = viewModelScope.launch {
        postsResponse = Response.Loading
        postUseCases.getPosts().collect { response ->
            postsResponse = response
        }
    }

    private fun imageEncoder(data: String): String =
        if (data.isEmpty()) data else URLEncoder.encode(data, StandardCharsets.UTF_8.toString())

    private fun stringEncoder(data: String): String {
        val regex = buildString { append("[?=&#+%/.; ]") }

        return data.replace(Regex(regex)) { matchResult ->
            when (matchResult.value) {
                "?" -> "%3F"
                "=" -> "%3D"
                "&" -> "%26"
                "#" -> "%23"
                "+" -> "%2B"
                "%" -> "%25"
                "/" -> "%2F"
                "." -> "%2E"
                ";" -> "%3B"
                " " -> "%20"
                else -> matchResult.value
            }
        }
    }

}

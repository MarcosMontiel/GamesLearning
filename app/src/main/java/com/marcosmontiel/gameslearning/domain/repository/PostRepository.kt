package com.marcosmontiel.gameslearning.domain.repository

import com.marcosmontiel.gameslearning.domain.model.Post
import com.marcosmontiel.gameslearning.domain.model.Response
import java.io.File

interface PostRepository {

    suspend fun create(post: Post, file: File): Response<Boolean>

}

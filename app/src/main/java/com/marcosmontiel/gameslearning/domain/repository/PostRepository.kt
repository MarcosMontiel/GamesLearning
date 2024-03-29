package com.marcosmontiel.gameslearning.domain.repository

import com.marcosmontiel.gameslearning.domain.model.Post
import com.marcosmontiel.gameslearning.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostRepository {

    fun getPosts(): Flow<Response<List<Post>>>

    fun getPostsByUser(userId: String): Flow<Response<List<Post>>>

    suspend fun create(post: Post, file: File): Response<Boolean>

    suspend fun update(post: Post, file: File?): Response<Boolean>

    suspend fun delete(postId: String): Response<Boolean>

    suspend fun deleteLike(postId: String, userId: String): Response<Boolean>

    suspend fun like(postId: String, userId: String): Response<Boolean>

}

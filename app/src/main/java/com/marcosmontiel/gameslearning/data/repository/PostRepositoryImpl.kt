package com.marcosmontiel.gameslearning.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import com.marcosmontiel.gameslearning.core.Constants.POSTS
import com.marcosmontiel.gameslearning.domain.model.Post
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.repository.PostRepository
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class PostRepositoryImpl @Inject constructor(
    @Named(POSTS) private val postsRef: CollectionReference,
    @Named(POSTS) private val storagePostsRef: StorageReference
) : PostRepository {

    override suspend fun create(post: Post, file: File): Response<Boolean> {
        TODO("Not yet implemented")
    }

}

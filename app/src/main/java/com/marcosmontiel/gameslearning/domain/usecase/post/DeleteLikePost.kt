package com.marcosmontiel.gameslearning.domain.usecase.post

import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.repository.PostRepository
import javax.inject.Inject

class DeleteLikePost @Inject constructor(private val repository: PostRepository) {

    suspend operator fun invoke(postId: String, userId: String): Response<Boolean> =
        repository.deleteLike(postId = postId, userId = userId)

}

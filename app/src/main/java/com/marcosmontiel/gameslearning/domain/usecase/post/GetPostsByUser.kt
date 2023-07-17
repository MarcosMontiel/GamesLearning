package com.marcosmontiel.gameslearning.domain.usecase.post

import com.marcosmontiel.gameslearning.domain.model.Post
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsByUser @Inject constructor(private val repository: PostRepository) {

    operator fun invoke(userId: String): Flow<Response<List<Post>>> =
        repository.getPostsByUser(userId = userId)

}

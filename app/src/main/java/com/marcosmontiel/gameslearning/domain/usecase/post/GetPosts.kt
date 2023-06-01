package com.marcosmontiel.gameslearning.domain.usecase.post

import com.marcosmontiel.gameslearning.domain.model.Post
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPosts @Inject constructor(private val repository: PostRepository) {

    operator fun invoke(): Flow<Response<List<Post>>> = repository.getPosts()

}

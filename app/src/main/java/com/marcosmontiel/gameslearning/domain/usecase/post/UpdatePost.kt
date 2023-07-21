package com.marcosmontiel.gameslearning.domain.usecase.post

import com.marcosmontiel.gameslearning.domain.model.Post
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.repository.PostRepository
import java.io.File
import javax.inject.Inject

class UpdatePost @Inject constructor(private val repository: PostRepository) {

    suspend operator fun invoke(post: Post, file: File?): Response<Boolean> =
        repository.update(post = post, file = file)

}

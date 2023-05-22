package com.marcosmontiel.gameslearning.domain.usecase.profile

import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.model.User
import com.marcosmontiel.gameslearning.domain.repository.ProfileRepository
import javax.inject.Inject

class Update @Inject constructor(private val repository: ProfileRepository) {

    suspend operator fun invoke(user: User): Response<Boolean> =
        repository.update(user = user)

}

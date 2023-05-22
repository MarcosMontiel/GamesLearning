package com.marcosmontiel.gameslearning.domain.usecase.profile

import com.marcosmontiel.gameslearning.domain.model.User
import com.marcosmontiel.gameslearning.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentProfile @Inject constructor(private val repository: ProfileRepository) {

    operator fun invoke(userId: String): Flow<User> = repository.currentProfile(userId = userId)

}

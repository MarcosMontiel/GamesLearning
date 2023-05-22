package com.marcosmontiel.gameslearning.domain.usecase.auth

import com.marcosmontiel.gameslearning.domain.repository.AuthRepository
import javax.inject.Inject

class Logout @Inject constructor(private val repository: AuthRepository) {

    operator fun invoke() = repository.logout()

}

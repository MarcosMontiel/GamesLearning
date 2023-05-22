package com.marcosmontiel.gameslearning.domain.usecase.auth

import com.google.firebase.auth.FirebaseUser
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.model.User
import com.marcosmontiel.gameslearning.domain.repository.AuthRepository
import javax.inject.Inject

class Register @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(user: User): Response<FirebaseUser> =
        repository.register(user = user)

}

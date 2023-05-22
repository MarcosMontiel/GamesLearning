package com.marcosmontiel.gameslearning.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.model.User

interface AuthRepository {

    fun logout()

    val currentUser: FirebaseUser?

    suspend fun register(user: User): Response<FirebaseUser>

    suspend fun login(email: String, password: String): Response<FirebaseUser>

}

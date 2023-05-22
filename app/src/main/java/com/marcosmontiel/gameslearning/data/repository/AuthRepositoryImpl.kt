package com.marcosmontiel.gameslearning.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.model.User
import com.marcosmontiel.gameslearning.domain.repository.AuthRepository
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override fun logout() = firebaseAuth.signOut()

    override val currentUser: FirebaseUser? get() = firebaseAuth.currentUser

    override suspend fun register(user: User): Response<FirebaseUser> {
        return try {
            val response = firebaseAuth.createUserWithEmailAndPassword(
                user.email,
                user.password
            ).await()
            Response.Success(data = response.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(exception = e)
        }
    }

    override suspend fun login(email: String, password: String): Response<FirebaseUser> {
        return try {
            val response = firebaseAuth.signInWithEmailAndPassword(
                email,
                password
            ).await()
            Response.Success(data = response.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(exception = e)
        }
    }

}

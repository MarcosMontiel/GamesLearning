package com.marcosmontiel.gameslearning.domain.repository

import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.io.File

interface ProfileRepository {

    fun currentProfile(userId: String): Flow<User>

    suspend fun create(user: User): Response<Boolean>

    suspend fun update(user: User): Response<Boolean>

    suspend fun savePhoto(file: File): Response<String>

}

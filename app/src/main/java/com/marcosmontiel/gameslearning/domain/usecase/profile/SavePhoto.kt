package com.marcosmontiel.gameslearning.domain.usecase.profile

import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.repository.ProfileRepository
import java.io.File
import javax.inject.Inject

class SavePhoto @Inject constructor(private val repository: ProfileRepository) {

    suspend operator fun invoke(file: File): Response<String> =
        repository.savePhoto(file = file)

}

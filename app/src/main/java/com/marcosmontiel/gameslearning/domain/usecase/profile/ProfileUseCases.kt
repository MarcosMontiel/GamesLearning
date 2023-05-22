package com.marcosmontiel.gameslearning.domain.usecase.profile

data class ProfileUseCases(
    val create: Create,
    val getCurrentProfile: GetCurrentProfile,
    val savePhoto: SavePhoto,
    val update: Update
)

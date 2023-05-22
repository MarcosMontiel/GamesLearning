package com.marcosmontiel.gameslearning.domain.usecase.auth

data class AuthUseCases(
    val getCurrentUser: GetCurrentUser,
    val login: Login,
    val logout: Logout,
    val register: Register
)

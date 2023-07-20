package com.marcosmontiel.gameslearning.presentation.screens.edit_post

data class EditPostState(
    val name: String = "",
    val category: String = "",
    val description: String = "",
    val image: String = "",
    val fieldsStatus: Boolean = true,
    val photoButtonStatus: Boolean = true,
    val publishButtonStatus: Boolean = false,
)

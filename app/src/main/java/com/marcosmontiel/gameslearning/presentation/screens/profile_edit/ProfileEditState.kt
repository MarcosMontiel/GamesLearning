package com.marcosmontiel.gameslearning.presentation.screens.profile_edit

data class ProfileEditState(
    val fieldsStatus: Boolean = true,
    val photoButtonStatus: Boolean = true,
    val photoSelectorDialogStatus: Boolean = false,
    val updateButtonStatus: Boolean = false,
)

package com.marcosmontiel.gameslearning.presentation.screens.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.marcosmontiel.gameslearning.domain.model.User
import com.marcosmontiel.gameslearning.domain.usecase.auth.AuthUseCases
import com.marcosmontiel.gameslearning.domain.usecase.profile.ProfileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val profileUseCases: ProfileUseCases
) : ViewModel() {

    // Current user data

    private val _currentUser: FirebaseUser = authUseCases.getCurrentUser()!!

    // LiveData

    private val _profileData = MutableLiveData<User>()
    val profileData: LiveData<User> = _profileData

    init {
        getCurrentProfile()
    }

    // Events

    fun onLogout() {
        authUseCases.logout()
    }

    // Private functions

    private fun getCurrentProfile() = viewModelScope.launch {
        profileUseCases.getCurrentProfile(_currentUser.uid).collect { data ->
            _profileData.value = data
        }
    }

}

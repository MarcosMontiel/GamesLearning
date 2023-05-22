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
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
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

    // Functions

    fun convertLiveDataToJson(): String {
        val profile: User = _profileData.value?.let { data ->
            if (data.avatar.isNotEmpty()) {
                val encoded = URLEncoder.encode(data.avatar, StandardCharsets.UTF_8.toString())
                data.copy(avatar = encoded)
            } else {
                data
            }
        } ?: User()
        return profile.toJson()
    }

    // Private functions

    private fun getCurrentProfile() = viewModelScope.launch {
        profileUseCases.getCurrentProfile(_currentUser.uid).collect { data ->
            _profileData.value = data
        }
    }

}

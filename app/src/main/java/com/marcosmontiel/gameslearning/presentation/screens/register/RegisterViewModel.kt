package com.marcosmontiel.gameslearning.presentation.screens.register

import android.util.Patterns
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.marcosmontiel.gameslearning.domain.model.Response
import com.marcosmontiel.gameslearning.domain.model.User
import com.marcosmontiel.gameslearning.domain.usecase.auth.AuthUseCases
import com.marcosmontiel.gameslearning.domain.usecase.profile.ProfileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val profileUseCases: ProfileUseCases
) : ViewModel() {

    // Late init variables

    private lateinit var _userData: User

    // Instances

    private val _passHiddenIcon: ImageVector = Icons.Rounded.VisibilityOff
    private val _passHiddenMask: VisualTransformation = PasswordVisualTransformation()
    private val _passVisibleIcon: ImageVector = Icons.Rounded.Visibility
    private val _passVisibleMask: VisualTransformation = VisualTransformation.None

    // State form

    var state by mutableStateOf(RegisterState())
        private set

    // LiveData

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _confirmPass = MutableLiveData<String>()
    val confirmPass: LiveData<String> = _confirmPass

    // Response

    private val _registerResponse = MutableStateFlow<Response<FirebaseUser>?>(value = null)
    val registerResponse: StateFlow<Response<FirebaseUser>?> = _registerResponse

    // Events

    fun onValueChange(username: String, email: String, password: String, confirmPass: String) {
        _username.value = username
        _email.value = email
        _password.value = password
        _confirmPass.value = confirmPass

        state = state.copy(
            saveButtonStatus = validateFields(username, email, password, confirmPass)
        )
    }

    fun onChangePasswordTransformation(status: Boolean) {
        state = state.copy(
            passwordDisplayStatus = status,
            passwordIcon = if (status) _passHiddenIcon else _passVisibleIcon,
            passwordMask = if (status) _passVisibleMask else _passHiddenMask,
        )
    }

    fun onChangeConfirmPasswordTransformation(status: Boolean) {
        state = state.copy(
            confirmPasswordDisplayStatus = status,
            confirmPasswordIcon = if (status) _passHiddenIcon else _passVisibleIcon,
            confirmPasswordMask = if (status) _passVisibleMask else _passHiddenMask,
        )
    }

    fun onRegisterUser() {
        _userData = User(
            username = _username.value!!,
            email = _email.value!!,
            password = _password.value!!
        )

        createRegister()
    }

    // Functions

    fun enableFields() {
        state = state.copy(
            fieldsStatus = true,
            saveButtonStatus = true,
        )

        _registerResponse.value = null
    }

    fun createProfile() = viewModelScope.launch {
        _userData.id = authUseCases.getCurrentUser()!!.uid
        profileUseCases.create(user = _userData)
    }

    // Private functions

    private fun disableFields() {
        state = state.copy(
            fieldsStatus = false,
            saveButtonStatus = false,
        )
    }

    private fun createRegister() = viewModelScope.launch {
        disableFields()

        _registerResponse.value = Response.Loading
        val result = authUseCases.register(user = _userData)
        _registerResponse.value = result
    }

    private fun validateFields(
        username: String,
        email: String,
        password: String,
        confirmPass: String
    ): Boolean = username.length > 8 && Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
            password.length > 6 && confirmPass.length > 6 && password == confirmPass

}

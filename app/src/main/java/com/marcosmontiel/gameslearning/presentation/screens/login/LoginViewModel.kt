package com.marcosmontiel.gameslearning.presentation.screens.login

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
import com.marcosmontiel.gameslearning.domain.usecase.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    // Current user data

    private val currentUser: FirebaseUser? = authUseCases.getCurrentUser()

    // Instances

    private val _passHiddenIcon: ImageVector = Icons.Rounded.VisibilityOff
    private val _passHiddenMask: VisualTransformation = PasswordVisualTransformation()
    private val _passVisibleIcon: ImageVector = Icons.Rounded.Visibility
    private val _passVisibleMask: VisualTransformation = VisualTransformation.None

    // State form

    var state by mutableStateOf(LoginState())
        private set

    // LiveData

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    // Login response

    private val _loginResponse = MutableStateFlow<Response<FirebaseUser>?>(value = null)
    val loginResponse: StateFlow<Response<FirebaseUser>?> = _loginResponse

    init {
        if (currentUser != null) {
            _loginResponse.value = Response.Success(data = currentUser)
        }
    }

    // Events

    fun onValueChange(email: String, password: String) {
        _email.value = email
        _password.value = password

        state = state.copy(
            loginButtonStatus = validateFields(email = email, password = password),
        )
    }

    fun onChangePasswordTransformation(status: Boolean) {
        state = state.copy(
            passwordDisplayStatus = status,
            passwordIcon = if (status) _passHiddenIcon else _passVisibleIcon,
            passwordMask = if (status) _passVisibleMask else _passHiddenMask,
        )
    }

    fun onLoginClick() = viewModelScope.launch {
        disableFields()

        _loginResponse.value = Response.Loading
        val response = authUseCases.login(email = email.value!!, password = password.value!!)
        _loginResponse.value = response
    }

    // Functions

    fun enableFields() {
        state = state.copy(
            fieldsStatus = true,
            loginButtonStatus = true,
        )

        _loginResponse.value = null
    }

    // Private functions

    private fun disableFields() {
        state = state.copy(
            fieldsStatus = false,
            loginButtonStatus = false,
        )
    }

    private fun validateFields(email: String, password: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6

}

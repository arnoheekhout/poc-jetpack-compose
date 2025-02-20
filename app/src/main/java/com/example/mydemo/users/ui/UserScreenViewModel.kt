// Stuk van project RISE
package com.example.mydemo.users.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydemo.mockdata.mockUsers
import com.example.mydemo.ui.users.UserError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException


// State van de viewmodel
sealed interface UserScreenUiState {
    data object Loading : UserScreenUiState
    data class Success(val user: User) : UserScreenUiState
    data class Error(val message: String) : UserScreenUiState
}

// Setting model
data class EmailSettings(
    val notifyOnOrder: Boolean = true,
    val notifyOnReaction: Boolean = true
)

// User model
data class User(
    val id: String,
    val name: String,
    val email: String,
    val phoneNumber: String
)

class UserScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UserScreenUiState>(UserScreenUiState.Loading)
    val uiState: StateFlow<UserScreenUiState> = _uiState.asStateFlow()

    var isEditing = mutableStateOf(false)

    private val _localUser = MutableStateFlow(User("", "", "", ""))
    val localUser: StateFlow<User> = _localUser

    private val _error = MutableStateFlow<UserError?>(null)
    val error: StateFlow<UserError?> = _error.asStateFlow()

    private val _emailSettings = MutableStateFlow(EmailSettings())
    val emailSettings: StateFlow<EmailSettings> = _emailSettings.asStateFlow()

    init {
        loadUser()
        loadEmailSettings()
    }

    private fun loadUser() {
        viewModelScope.launch {
            try {
                mockUsers.firstOrNull()?.let { user ->
                    _uiState.value = UserScreenUiState.Success(user)
                    _localUser.value = user
                } ?: run {
                    _error.value = UserError.UserNotFound
                }
            } catch (e: Exception) {
                _error.value = UserError.UnknownError(e)
            }
        }
    }

    private fun loadEmailSettings() {
        viewModelScope.launch {
            try {
                _emailSettings.value = EmailSettings()
            } catch (e: Exception) {
                _error.value = UserError.UnknownError(e)
            }
        }
    }

    fun updateLocalUser(
        name: String? = null,
        email: String? = null,
        phoneNumber: String? = null
    ) {
        _localUser.value = _localUser.value.copy(
            name = name ?: _localUser.value.name,
            email = email ?: _localUser.value.email,
            phoneNumber = phoneNumber ?: _localUser.value.phoneNumber
        )
        validateFields()
    }

    private fun validateFields(): UserError.ValidationError? {
        val nameError = if (_localUser.value.name.isBlank()) "Name cannot be empty" else null
        val phoneError = if (!android.util.Patterns.PHONE.matcher(_localUser.value.phoneNumber).matches()) "Phone cannot be empty or invalid" else null
        var emailError = if(!android.util.Patterns.EMAIL_ADDRESS.matcher(_localUser.value.email).matches()) "Email cannot be empty or invalid" else null

        return if (nameError != null || emailError != null || phoneError != null) {
            UserError.ValidationError(nameError, emailError, phoneError)
        } else null
    }

    fun updateUser() {
        viewModelScope.launch {
            try {
                val validationError = validateFields()
                if (validationError != null) {
                    _error.value = validationError
                    return@launch
                }

                _uiState.value = UserScreenUiState.Success(_localUser.value)
                isEditing.value = false
                _error.value = null
            } catch (e: Exception) {
                _error.value = when (e) {
                    is IOException -> UserError.NetworkError
                    else -> UserError.UnknownError(e)
                }
            }
        }
    }

    fun updateEmailSettings(
        notifyOnOrder: Boolean? = null,
        notifyOnReaction: Boolean? = null
    ) {
        _emailSettings.value = _emailSettings.value.copy(
            notifyOnOrder = notifyOnOrder ?: _emailSettings.value.notifyOnOrder,
            notifyOnReaction = notifyOnReaction ?: _emailSettings.value.notifyOnReaction
        )
        saveEmailSettings()
    }

    private fun saveEmailSettings() {
        viewModelScope.launch {
            try {
                Log.d("Email settings saved", _emailSettings.value.toString())
            } catch (e: Exception) {
                _error.value = UserError.UnknownError(e)
            }
        }
    }

}
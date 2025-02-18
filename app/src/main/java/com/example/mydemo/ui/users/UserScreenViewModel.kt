package com.example.mydemo.ui.users

import androidx.lifecycle.ViewModel
import com.example.mydemo.mockdata.User
import com.example.mydemo.mockdata.mockUsers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Use sealed interface/class for state pattern
sealed interface UserScreenUiState {
    data object Loading : UserScreenUiState
    data class Success(val user: User) : UserScreenUiState
    data class Error(val errorMessage: String) : UserScreenUiState
}

class UserScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UserScreenUiState>(UserScreenUiState.Loading)
    val uiState: StateFlow<UserScreenUiState> = _uiState.asStateFlow()

    init {
        loadUser()
    }

    private fun loadUser() {
        mockUsers.firstOrNull()?.let { user ->
            _uiState.value = UserScreenUiState.Success(user)
        } ?: run {
            _uiState.value = UserScreenUiState.Error("No user found")
        }
    }
}
// Copy paste project RISE
package com.example.mydemo.ui.users

sealed interface UserError {
    data object NetworkError : UserError
    data object UserNotFound : UserError
    data class ValidationError(
        val nameError: String? = null,
        val emailError: String? = null,
        val phoneError: String? = null
    ) : UserError
    data class UpdateError(val message: String) : UserError
    data class UnknownError(val exception: Throwable) : UserError
}

fun UserError.getErrorMessage(): String = when (this) {
    is UserError.NetworkError -> "Network connection error. Please check your connection."
    is UserError.UserNotFound -> "User not found."
    is UserError.ValidationError -> "Please check your input values."
    is UserError.UpdateError -> message
    is UserError.UnknownError -> "An unexpected error occurred: ${exception.message}"
}
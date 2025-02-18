package com.example.mydemo.ui.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mydemo.mockdata.User

@Composable
fun UserContent(
    user: User,
    isEditing: Boolean,
    error: UserError?,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onEditClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        when (val currentError = error) {
            is UserError.NetworkError,
            is UserError.UnknownError -> {
                Text(
                    text = currentError.getErrorMessage(),
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            else -> { /* Doe niks */ }
        }

        val validationError = error as? UserError.ValidationError

        EditableField(
            label = "Name",
            value = user.name,
            isEditing = isEditing,
            onValueChange = onNameChange,
            errorMessage = validationError?.nameError
        )

        Spacer(modifier = Modifier.height(8.dp))

        EditableField(
            label = "Email",
            value = user.email,
            isEditing = isEditing,
            onValueChange = onEmailChange,
            errorMessage = validationError?.emailError
        )

        Spacer(modifier = Modifier.height(8.dp))

        EditableField(
            label = "Phone Number",
            value = user.phoneNumber,
            isEditing = isEditing,
            onValueChange = onPhoneChange,
            errorMessage = validationError?.phoneError
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isEditing) {
            Button(
                onClick = onSaveClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }
        } else {
            Button(
                onClick = onEditClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Edit")
            }
        }
    }
}


// Komt uit RISE project
@Composable
fun EditableField(
    label: String,
    value: String,
    isEditing: Boolean,
    onValueChange: (String) -> Unit,
    errorMessage: String? = null,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            enabled = isEditing,
            isError = errorMessage != null,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                errorContainerColor = MaterialTheme.colorScheme.errorContainer,
            )
        )
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}
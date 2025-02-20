package com.example.mydemo.users.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mydemo.ui.users.UserContent
import com.example.mydemo.ui.users.UserScreenUiState
import com.example.mydemo.ui.users.UserScreenViewModel

@Composable
fun UserScreen(
    viewModel: UserScreenViewModel = viewModel(),
    onEditDetailsClick: () -> Unit,
    onNotificationSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is UserScreenUiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }
        is UserScreenUiState.Success -> {
            val localUser by viewModel.localUser.collectAsState()

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Title and Email
                Text(
                    text = "My Account", // Replace with string resource T5949
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = localUser.email,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Buttons
                OutlinedButton(
                    onClick = onEditDetailsClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Edit my details") // Replace with string resource T5952
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = onNotificationSettingsClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Email settings") // Replace with string resource T5956
                }
            }
        }
        is UserScreenUiState.Error -> {
            Text(
                text = (uiState as UserScreenUiState.Error).message,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun EditScreen(
    viewModel: UserScreenViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val localUser by viewModel.localUser.collectAsState()
    val error by viewModel.error.collectAsState()
    val isEditing by viewModel.isEditing

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        when (uiState) {
            is UserScreenUiState.Loading -> {
                CircularProgressIndicator()
            }
            is UserScreenUiState.Success -> {
                UserContent(
                    user = localUser,
                    isEditing = isEditing,
                    error = error,
                    onNameChange = { viewModel.updateLocalUser(name = it) },
                    onEmailChange = { viewModel.updateLocalUser(email = it) },
                    onPhoneChange = { viewModel.updateLocalUser(phoneNumber = it) },
                    onEditClick = { viewModel.isEditing.value = true },
                    onSaveClick = { viewModel.updateUser() }
                )
            }
            is UserScreenUiState.Error -> {
                Text(
                    text = (uiState as UserScreenUiState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
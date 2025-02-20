package com.example.mydemo.users.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mydemo.ui.users.UserContent

@Composable
fun UserScreen(
    onEditButtonClicked: () -> Unit,
    onEmailButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UserScreenViewModel = viewModel()
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
                Text(
                    text = "My Account",
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

                OutlinedButton(
                    onClick = onEditButtonClicked,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Edit my details")
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = onEmailButtonClicked,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Email settings")
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
    modifier: Modifier = Modifier,
    viewModel: UserScreenViewModel = viewModel()
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

@Composable
fun EmailScreen(
    modifier: Modifier = Modifier,
    viewModel: UserScreenViewModel = viewModel()
) {
    val emailSettings by viewModel.emailSettings.collectAsState()

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                "When I placed an order"
            )
            Checkbox(
                checked = emailSettings.notifyOnOrder,
                onCheckedChange = { viewModel.updateEmailSettings(notifyOnOrder = it) }
            )
        }

        Row (
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                "When I got a reaction to an order"
            )
            Checkbox(
                checked = emailSettings.notifyOnReaction,
                onCheckedChange = { viewModel.updateEmailSettings(notifyOnReaction = it) }
            )
        }
    }
}


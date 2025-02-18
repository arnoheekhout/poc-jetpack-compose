package com.example.mydemo.ui.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mydemo.ui.theme.DemoTheme

@Composable
fun UserScreen(
    onNextButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UserScreenViewModel = viewModel()
) {
    var isEditing by viewModel.isEditing
    val localUser by viewModel.localUser.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val error by viewModel.error.collectAsState()

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
                    onEditClick = { isEditing = true },
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

@Preview
@Composable
fun UserScreenPreview() {
    DemoTheme {
        UserScreen(
            onNextButtonClicked = TODO(),
            modifier = TODO()
        )
    }
}
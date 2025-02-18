package com.example.mydemo.ui.users

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UserScreen(
    onNextButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UserScreenViewModel = viewModel()) {

    val currentUser by viewModel.uiState.collectAsState()

    Log.d("UserScreen", "Current user: $currentUser")

    Surface(color = MaterialTheme.colorScheme.primary) {
        Text(
            text = "This will be our user overview!",
            modifier = modifier.padding(24.dp)
        )
    }
}

@Preview
@Composable
fun UserScreenPreview() {
    UserScreen(
        onNextButtonClicked = TODO(),
        modifier = TODO()
    )
}
package com.example.mydemo.home.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mydemo.R
import com.example.mydemo.common.composables.theme.DemoTheme

@Composable
fun HomeScreen(
    onNextButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val appName = stringResource(R.string.app_name)
    Surface(
        color = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Text(
            text = "Welcome to $appName",
            modifier = modifier.padding(24.dp),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    DemoTheme {
        HomeScreen(
            onNextButtonClicked = TODO(),
            modifier = Modifier
        )
    }
}
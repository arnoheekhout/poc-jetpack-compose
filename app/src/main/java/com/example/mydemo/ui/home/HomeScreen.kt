package com.example.mydemo.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onNextButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier) {
        Surface(color = Color.Red) {
            Text(
                text = "This will be our homepage!",
                modifier = modifier.padding(24.dp)
            )
        }
    }

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onNextButtonClicked = TODO(),
        modifier = TODO()
    )
}
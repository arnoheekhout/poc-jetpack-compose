package com.example.mydemo.ui.home

import android.provider.Settings.Global.getString
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mydemo.R
import androidx.compose.ui.res.stringResource

@Composable
fun HomeScreen(
    onNextButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier) {

        val appName = stringResource(R.string.app_name)
        Surface(color = Color.Red) {
            Text(
                text = "Welcome to $appName",
                modifier = modifier.padding(24.dp)
            )
        }
    }

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onNextButtonClicked = TODO(),
        modifier = Modifier
    )
}
package com.example.mydemo.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
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
        Column(  // Added Column to arrange elements vertically
            modifier = modifier.padding(24.dp)
        ) {
            Text(
                text = "Welcome to $appName",
                color = MaterialTheme.colorScheme.onSurface
            )

            // Via het internet
            AsyncImage(
                // logo header small
                model = "https://bakeronline.be/assets/images/bakeronline/logo-header-small.svg",
                contentDescription = "Bakeronline logo",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(200.dp) 
            )

            // Via disk
            Image(
                painter = painterResource(id = R.drawable.logo_header_small),
                contentDescription = "Logo header small"
            )
        }
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
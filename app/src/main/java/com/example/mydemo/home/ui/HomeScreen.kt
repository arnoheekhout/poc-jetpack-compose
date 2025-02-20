package com.example.mydemo.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.mydemo.DemoScreens
import com.example.mydemo.R

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Column(
            modifier = modifier.padding(24.dp)
        ) {
            FlavorComponent(modifier=modifier)

            Spacer(modifier = Modifier.size(24.dp))

            NetworkComponent(modifier=modifier)

            Spacer(modifier = Modifier.size(24.dp))

            TermsAndConditionsComponent(
                modifier = Modifier.size(24.dp),
                onClick = { navController.navigate(DemoScreens.Terms.name) }
                )
        }
    }
}

@Composable
fun TermsAndConditionsComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    TextButton(
        onClick = onClick
    ) {
        Text("Terms and conditions and privacy policy")
    }
}

@Composable
fun FlavorComponent(modifier: Modifier = Modifier) {
    val appName = stringResource(R.string.app_name)

    Column(
        modifier = modifier
    ) {
        Text(
            text = "Flavor demo",
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Welcome to $appName",
            color = MaterialTheme.colorScheme.onSurface
        )

        // Via disk
        Image(
            painter = painterResource(id = R.drawable.logo_header_small),
            contentDescription = "Logo header small"
        )
    }
}


@Composable
fun NetworkComponent(modifier: Modifier = Modifier) {
    Column (modifier = modifier) {
        Text(
            text = "Images via netwerk demo",
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
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
    }
}
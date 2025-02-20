package com.example.mydemo.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
        contentColor = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            FlavorComponent(modifier = Modifier.fillMaxWidth())

            NetworkComponent(modifier = Modifier.fillMaxWidth())

            TermsAndConditionsComponent(
                modifier = Modifier.fillMaxWidth(),
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
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text = "Terms and Conditions and Privacy Policy",
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun FlavorComponent(modifier: Modifier = Modifier) {
    val appName = stringResource(R.string.app_name)

    Column(
        modifier = modifier
    ) {
        Text(
            text = "Flavor Demo",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = "Welcome to $appName",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
        )

        Spacer(modifier = Modifier.size(16.dp))

        // Via disk
        Image(
            painter = painterResource(id = R.drawable.logo_header_small),
            contentDescription = "Logo header small",
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun NetworkComponent(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Images via Network Demo",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.size(16.dp))

            // Via het internet
            AsyncImage(
                model = "https://bakeronline.be/assets/images/bakeronline/logo-header-small.svg",
                contentDescription = "Bakeronline logo",
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}
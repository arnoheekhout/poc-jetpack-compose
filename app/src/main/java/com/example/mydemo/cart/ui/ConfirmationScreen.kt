package com.example.mydemo.cart.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mydemo.cart.composables.CartViewModel

@Composable
fun ConfirmationScreen(
    navController: NavController,
    cartViewModel: CartViewModel = viewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Bedankt voor je bestelling!",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                cartViewModel.clearCart()
                navController.navigate("home") {
                    popUpTo("cartScreen") { inclusive = true }
                }
            }) {
                Text("Keer terug naar home")
            }
        }
    }
}
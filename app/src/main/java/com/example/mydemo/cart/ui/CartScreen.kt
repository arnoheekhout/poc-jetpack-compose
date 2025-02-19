package com.example.mydemo.cart.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mydemo.cart.composables.CartViewModel

@Composable
fun CartScreen(
    navController: NavController, cartViewModel: CartViewModel = viewModel(navController.currentBackStackEntry!!)
) {
    val cartItems = cartViewModel.cartItems.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Winkelmand", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        if (cartItems.isEmpty()) {
            Text("Je winkelmand is leeg.")
        } else {
            LazyColumn {
                items(cartItems) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(item.name, style = MaterialTheme.typography.bodyLarge)
                            Text("Prijs: €${String.format("%.2f", item.price)}")
                            Text("Aantal: ${item.quantity}")
                            Text("Totaal: €${String.format("%.2f", item.price * item.quantity)}")

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Button(
                                    onClick = { cartViewModel.removeFromCart(item.name) },
                                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                                ) {
                                    Text("Verwijder")
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { cartViewModel.clearCart() }, modifier = Modifier.fillMaxWidth()
            ) {
                Text("Leeg winkelmand")
            }
        }
    }
}
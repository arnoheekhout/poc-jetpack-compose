package com.example.mydemo.cart.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mydemo.cart.composables.CartViewModel
import com.example.mydemo.common.composables.BackButton
import com.example.mydemo.common.composables.PayButton

@Composable
fun CartScreen(
    navController: NavController, cartViewModel: CartViewModel = viewModel()
) {
    val cartItems = cartViewModel.cartItems.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Winkelmand", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        PayButton(onClick = { navController.navigate("CartScreen/{DateTimePicker}") })

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
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(item.name, style = MaterialTheme.typography.bodyLarge)
                                    Text("Prijs: €${String.format("%.2f", item.price)}")
                                    Text("Aantal: ${item.quantity}")
                                    Text(
                                        "Totaal: €${
                                            String.format(
                                                "%.2f",
                                                item.price * item.quantity
                                            )
                                        }"
                                    )
                                }
                                Column(
                                    horizontalAlignment = Alignment.End,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    IconButton(
                                        onClick = { cartViewModel.removeFromCart(item.name) }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Delete",
                                            tint = MaterialTheme.colorScheme.error
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(8.dp))

                                    item.Image?.let {
                                        Image(
                                            bitmap = it.asImageBitmap(),
                                            contentDescription = item.name,
                                            modifier = Modifier
                                                .width(100.dp)
                                                .height(100.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
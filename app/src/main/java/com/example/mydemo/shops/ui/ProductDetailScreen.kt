package com.example.mydemo.shops.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mydemo.common.composables.BackButton
import com.example.mydemo.shops.composables.ProductViewModel
import com.example.mydemo.shops.factories.ProductViewModelFactory
import com.example.mydemo.shops.repository.ProductRepository

@Composable
fun ProductDetailScreen(
    navController: NavController,
    shopName: String,
    category: String,
    productName: String,
    productRepository: ProductRepository = ProductRepository(),
    viewModel: ProductViewModel = viewModel(factory = ProductViewModelFactory(productRepository))
) {
    viewModel.loadProduct(productName)
    val currentProduct = viewModel.product.collectAsState().value
    var quantity by remember { mutableStateOf(1) }

    currentProduct?.let { product ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            BackButton(onBack = { navController.popBackStack() })
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = product.name, style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Price per stuk: €${String.format("%.2f", product.price)}", fontSize = 18.sp)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Quantity selector!
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { if (quantity > 1) quantity-- },
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text(text = "-", fontSize = 20.sp)
                        }

                        Text(text = "$quantity", fontSize = 20.sp)

                        Button(
                            onClick = { quantity++ },
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text(text = "+", fontSize = 20.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Calculation
                    val totalPrice = product.price * quantity
                    Text(
                        text = "Totaal: €${String.format("%.2f", totalPrice)}",
                        fontSize = 22.sp,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Add to basket button TODO
                    Button(
                        onClick = {
                            // TODO: Voeg product + quantity toe aan winkelmand
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Text(text = "+ ADD TO BASKET", fontSize = 18.sp)
                    }
                }
            }
        }
    } ?: Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Product not found", style = MaterialTheme.typography.bodyLarge)
    }
}
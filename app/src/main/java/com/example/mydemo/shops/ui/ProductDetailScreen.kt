package com.example.mydemo.shops.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
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

    currentProduct?.let {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(text = it.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Price: €${it.price}", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { if (quantity > 1) quantity-- }) {
                    Text(text = "-")
                }
                Text(text = "$quantity", style = MaterialTheme.typography.bodyMedium)
                Button(onClick = { quantity++ }) {
                    Text(text = "+")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                // TODO: Voeg product toe aan winkelmand
            }) {
                Text(text = "Add to Cart")
            }
        }
    } ?: Text("Product niet gevonden", style = MaterialTheme.typography.bodyLarge)
}
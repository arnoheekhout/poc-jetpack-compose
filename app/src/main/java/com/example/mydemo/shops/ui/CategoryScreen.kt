package com.example.mydemo.shops.ui

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext
import com.example.mydemo.shops.composables.CategoryViewModel
import com.example.mydemo.shops.factories.CategoryViewModelFactory
import com.example.mydemo.shops.repository.ProductRepository
import com.example.mydemo.common.composables.BackButton

@Composable
fun CategoryScreen(
    navController: NavController,
    shopName: String,
    productRepository: ProductRepository = ProductRepository(LocalContext.current),
    viewModel: CategoryViewModel = viewModel(factory = CategoryViewModelFactory(productRepository))
) {
    val categories = viewModel.categories.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        BackButton(
            onBack = { navController.popBackStack() }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = shopName, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(categories.keys.toList()) { category ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            navController.navigate("productListScreen/$shopName/$category")
                        }) {
                    Text(
                        text = category,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
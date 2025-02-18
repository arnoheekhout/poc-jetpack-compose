package com.example.mydemo.shops.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mydemo.shops.composables.ShopViewModel

@Composable
fun ShopScreen(navController: NavController, viewModel: ShopViewModel = viewModel()) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredBakers by viewModel.filteredBakers.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding())
}
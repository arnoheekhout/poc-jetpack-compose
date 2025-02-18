package com.example.mydemo.shops.composables

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ShopViewModel: ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val diffBakers = listOf(
        Baker("Bakkerij Joeri", "Antwerpen"),
        Baker("Bakkerij Joeri", "Wichelen"),
        Baker("Joeri Hamme", "Hamme"),
        Baker("Bakkerij Tartan", "Serskamp"),
        Baker("Bakkerij Tuytelaers", "Arendonk"),
        Baker("Bakkerij Gregory", "Stekene")
    )
}

data class Baker(val name: String, val location: String)
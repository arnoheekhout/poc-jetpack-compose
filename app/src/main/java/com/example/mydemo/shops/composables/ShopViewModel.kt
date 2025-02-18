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
        Baker("Bakkerij Gregory", "Stekene"),
        Baker("Bakkerij Thienpondt", "Affligem"),
        Baker("Thienpondt BVBA", "Waasmunster"),
        Baker("Bakkerij Lippens", "Sint-Martems-Latem"),
        Baker("Bakkerij Boll", "Dendermonde")
    )

    private val _filteredBakers = MutableStateFlow(diffBakers)
    val filteredBakers: StateFlow<List<Baker>> = _filteredBakers

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        _filteredBakers.value = diffBakers.filter {
            it.name.contains(query, ignoreCase = true) || it.location.contains(query, ignoreCase = true)
        }
    }
}

data class Baker(val name: String, val location: String)
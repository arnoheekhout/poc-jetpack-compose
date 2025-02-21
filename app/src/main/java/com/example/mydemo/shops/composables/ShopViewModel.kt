package com.example.mydemo.shops.composables

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.mydemo.shops.models.Baker

class ShopViewModel: ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val diffBakers = listOf(
        Baker("Bakkerij Joeri", "Demostraat 1,", "Antwerpen"),
        Baker("Bakkerij Joeri", "Demostraat 2,", "Wichelen"),
        Baker("Bakkerij Tartan", "Demostraat 3,", "Hamme"),
        Baker("Bakkerij Tartan", "Demostraat 4,", "Serskamp"),
        Baker("Bakkerij Tuytelaers", "Demostraat 5,", "Arendonk"),
        Baker("Bakkerij Gregory", "Demostraat 6,", "Antwerpen"),
        Baker("Bakkerij Thienpondt", "Demostraat 7,", "Affligem"),
        Baker("Bakkerij Timaya", "Demostraat 8,", "Waasmunster"),
        Baker("Bakkerij Lippens", "Demostraat 9,", "Sint-Martems-Latem"),
        Baker("Bakkerij Boll", "Demostraat 10,", "Wichelen")
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
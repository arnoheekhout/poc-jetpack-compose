package com.example.mydemo.shops.composables

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoryViewModel : ViewModel() {
    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories = _categories.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        _categories.value = listOf("Brood", "Ontbijtkoeken", "Pistolets en franse broden", "Patisserie", "Plastiek zakje", "Other categories")
    }
}
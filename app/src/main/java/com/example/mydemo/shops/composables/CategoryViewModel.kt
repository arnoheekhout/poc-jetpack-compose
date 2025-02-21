package com.example.mydemo.shops.composables

import androidx.lifecycle.ViewModel
import com.example.mydemo.shops.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.mydemo.shops.repository.ProductRepository

class CategoryViewModel(private val productRepository: ProductRepository) : ViewModel() {
    private val _categories = MutableStateFlow<Map<String, List<Product>>>(emptyMap())
    val categories = _categories.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        _categories.value = productRepository.getProduct()
    }
}
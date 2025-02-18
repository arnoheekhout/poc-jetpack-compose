package com.example.mydemo.shops.composables

import androidx.lifecycle.ViewModel
import com.example.mydemo.shops.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.mydemo.shops.repository.ProductRepository

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {
    private val _product = MutableStateFlow<Product?>(null)
    val product = _product.asStateFlow()

    fun loadProduct(productName: String) {
        _product.value = productRepository.getProductByName(productName)
    }
}
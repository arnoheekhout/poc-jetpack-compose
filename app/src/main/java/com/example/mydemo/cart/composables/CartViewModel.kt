package com.example.mydemo.cart.composables

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.mydemo.cart.models.CartItem
import com.example.mydemo.shops.models.Product

class CartViewModel : ViewModel() {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems = _cartItems.asStateFlow()

    fun addToCart(product: Product, quantity: Int) {
        val currentList = _cartItems.value.toMutableList()
        val existingItem = currentList.find { it.name == product.name }

        if (existingItem != null) {
            existingItem.quantity += quantity
        } else {
            currentList.add(CartItem(product.name, product.price, quantity))
        }
        _cartItems.value = currentList
    }

    fun removeFromCart(productName: String) {
        _cartItems.value = _cartItems.value.filterNot { it.name == productName }
    }
}
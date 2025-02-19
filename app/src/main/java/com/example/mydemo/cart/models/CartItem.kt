package com.example.mydemo.cart.models

data class CartItem(
    val name: String,
    val price: Double,
    var quantity: Int
)
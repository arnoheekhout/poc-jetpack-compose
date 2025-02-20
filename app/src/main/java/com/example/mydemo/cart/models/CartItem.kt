package com.example.mydemo.cart.models

import android.graphics.Bitmap

data class CartItem(
    val name: String,
    val price: Double,
    var quantity: Int,
    val Image: Bitmap?
)
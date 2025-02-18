package com.example.mydemo.common.previews

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

class FakeNavController : NavController(null) {
    override fun navigate(route: String, builder: NavOptionsBuilder.() -> Unit) {
        // Do nothing, for preview purposes
    }
}

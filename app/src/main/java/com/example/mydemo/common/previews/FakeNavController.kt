package com.example.mydemo.common.previews

import android.app.Application
import androidx.navigation.NavController

// Fake NavController voor previews zonder navigatiefunctionaliteit.
class FakeNavController(application: Application) : NavController(application)

package com.example.mydemo.home.ui

import android.content.Context
import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri

@Composable
fun TermsAndConditionsScreen() {
    val context = LocalContext.current
    Button(onClick = {
        openUrlInBrowser(context, "https://bakeronline.be/be-en/policy/tac")
    }) {
        Text("Terms and Conditions")
    }


    Button(onClick = {
        openUrlInBrowser(context, "https://bakeronline.be/be-en/policy/privacy")
    }) {
        Text("Privacy policy")
    }
}


private fun openUrlInBrowser(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
    context.startActivity(intent)
}
package com.example.mydemo.common.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import com.squareup.picasso.Picasso

@Composable
fun PicassoImage(url: String) {
    val context = LocalContext.current
    val imageBitmap = remember { mutableStateOf<ImageBitmap?>(null) }

    Picasso.get().load(url).into(object : Target {

    }
}
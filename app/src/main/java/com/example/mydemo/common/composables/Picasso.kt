package com.example.mydemo.common.composables

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

@Composable
fun PicassoImage(url: String, modifier: Modifier = Modifier) {
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    val picassoTarget = remember {
        object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                bitmap?.let {
                    imageBitmap = it.asImageBitmap()
                }
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                e?.printStackTrace()
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                // TODO placeholder (in res/drawable/)
            }
        }
    }

    LaunchedEffect(url) {
        Picasso.get().load(url).into(picassoTarget)
    }

    imageBitmap?.let {
        Image(bitmap = it, contentDescription = "Afbeelding", modifier = modifier)
    }
}
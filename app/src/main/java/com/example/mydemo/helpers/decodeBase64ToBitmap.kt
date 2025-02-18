package com.example.mydemo.helpers

import android.content.Context
import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayInputStream
import android.graphics.BitmapFactory

fun decodeBase64ToBitmap(base64String: String): Bitmap? {
    return try {
        val decodedString = Base64.decode(base64String, Base64.DEFAULT)
        BitmapFactory.decodeStream(ByteArrayInputStream(decodedString))
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun Context.readBase64FromRaw(resourceId: Int): String? {
    return try {
        resources.openRawResource(resourceId).bufferedReader().use { it.readText() }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
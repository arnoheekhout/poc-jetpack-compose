package com.example.mydemo.ui.theme

import android.content.Context
import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.example.mydemo.R

class ColorSchemeProvider(context: Context) {
    // Kleuren cachen (composable niet zo performant)
    private val colors = object {
        val primaryDark = Color(ContextCompat.getColor(context, R.color.md_primary_dark))
        val onPrimaryDark = Color(ContextCompat.getColor(context, R.color.md_onPrimary_dark))
        val secondaryDark = Color(ContextCompat.getColor(context, R.color.md_secondary_dark))
        val onSecondaryDark = Color(ContextCompat.getColor(context, R.color.md_onSecondary_dark))
        val tertiaryDark = Color(ContextCompat.getColor(context, R.color.md_tertiary_dark))
        val onTertiaryDark = Color(ContextCompat.getColor(context, R.color.md_onTertiary_dark))
        val backgroundDark = Color(ContextCompat.getColor(context, R.color.md_background_dark))
        val surfaceDark = Color(ContextCompat.getColor(context, R.color.md_surface_dark))

        val primaryLight = Color(ContextCompat.getColor(context, R.color.md_primary_light))
        val onPrimaryLight = Color(ContextCompat.getColor(context, R.color.md_onPrimary_light))
        val secondaryLight = Color(ContextCompat.getColor(context, R.color.md_secondary_light))
        val onSecondaryLight = Color(ContextCompat.getColor(context, R.color.md_onSecondary_light))
        val tertiaryLight = Color(ContextCompat.getColor(context, R.color.md_tertiary_light))
        val onTertiaryLight = Color(ContextCompat.getColor(context, R.color.md_onTertiary_light))
        val backgroundLight = Color(ContextCompat.getColor(context, R.color.md_background_light))
        val surfaceLight = Color(ContextCompat.getColor(context, R.color.md_surface_light))
    }

    private val darkScheme = darkColorScheme(
        primary = colors.primaryDark,
        onPrimary = colors.onPrimaryDark,
        secondary = colors.secondaryDark,
        onSecondary = colors.onSecondaryDark,
        tertiary = colors.tertiaryDark,
        onTertiary = colors.onTertiaryDark,
        background = colors.backgroundDark,
        surface = colors.surfaceDark
    )

    private val lightScheme = lightColorScheme(
        primary = colors.primaryLight,
        onPrimary = colors.onPrimaryLight,
        secondary = colors.secondaryLight,
        onSecondary = colors.onSecondaryLight,
        tertiary = colors.tertiaryLight,
        onTertiary = colors.onTertiaryLight,
        background = colors.backgroundLight,
        surface = colors.surfaceLight
    )

    fun getColorScheme(
        context: Context,
        darkTheme: Boolean,
        dynamicColor: Boolean
    ): ColorScheme {
        return when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                if (darkTheme) dynamicDarkColorScheme(context)
                else dynamicLightColorScheme(context)
            }
            darkTheme -> darkScheme
            else -> lightScheme
        }
    }
}
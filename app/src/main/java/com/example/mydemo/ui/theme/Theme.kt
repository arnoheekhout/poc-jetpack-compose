package com.example.mydemo.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = md_primary_dark,
    onPrimary = md_onPrimary_dark,
    secondary = md_secondary_dark,
    onSecondary = md_onSecondary_dark,
    tertiary = md_tertiary_dark,
    onTertiary = md_onTertiary_dark,
    background = md_background_dark,
    surface = md_surface_dark,
)

private val LightColorScheme = lightColorScheme(
    primary = md_primary_light,
    onPrimary = md_onPrimary_light,
    secondary = md_secondary_light,
    onSecondary = md_onSecondary_light,
    tertiary = md_tertiary_light,
    onTertiary = md_onTertiary_light,
    background = md_background_light,
    surface = md_surface_light,
)

@Composable
fun DemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
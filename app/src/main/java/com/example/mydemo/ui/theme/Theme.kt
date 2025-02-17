package com.example.mydemo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Colors from the original theme
val Main = Color(0x00) // Add your main color hex
val MainDark = Color(0xFF) // Add your main dark color hex
val Secondary = Color(0xFF) // Add your secondary color hex
val TextDark = Color(0xFF) // Add your text dark color hex
val TextGray = Color(0xFF) // Add your text gray color hex
val Error = Color(0xFF) // Add your error color hex
val Background = Color(0xFF) // Add your background color hex

private val LightColorScheme = lightColorScheme(
    primary = Main,
    onPrimary = Color.White,
    primaryContainer = MainDark,
    secondary = Secondary,
    background = Background,
    surface = Background,
    error = Error,
    onSecondary = Color.White,
    onBackground = TextDark,
    onSurface = TextDark,
)

private val DarkColorScheme = darkColorScheme(
    primary = MainDark,
    onPrimary = Color.White,
    primaryContainer = Main,
    secondary = Secondary,
    background = Color.Black,
    surface = Color.Black,
    error = Error,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
)

// Typography based on the original styles
val TypographyVal = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 36.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 36.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 24.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 14.sp,
        lineHeight = 21.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )
)

object AppTheme {
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(8.dp),
        large = RoundedCornerShape(12.dp)
    )

    // Custom dimensions based on the original theme
    object Dimensions {
        val globalMargin = 16.dp
        val globalMarginHalf = 8.dp
        val inputHeight = 56.dp
        val inputPaddingTop = 16.dp
        val elevationButtonResting = 2.dp
    }
}

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = TypographyVal,
        shapes = AppTheme.shapes,
        content = content
    )
}

// Extension for common text styles based on the original theme
object TextStyles {
    val itemTitle: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

    val itemSubtitle: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 14.sp,
            color = TextGray
        )

    val itemSubtitlePrice: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

    val buttonStyle: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
}
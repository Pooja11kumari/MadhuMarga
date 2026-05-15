package com.example.madhu_marga.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = HoneyPrimaryDark,
    secondary = HoneySecondaryDark,
    tertiary = HoneySecondary,
    background = HoneyBackgroundDark,
    surface = HoneySurfaceDark,
    onPrimary = HoneyText,
    onSecondary = HoneyText,
    onTertiary = HoneyText,
    onBackground = HoneyTextDark,
    onSurface = HoneyTextDark
)

private val LightColorScheme = lightColorScheme(
    primary = HoneyPrimary,
    secondary = HoneySecondary,
    tertiary = HoneyTertiary,
    background = HoneyBackground,
    surface = HoneySurface,
    onPrimary = Color.White,
    onSecondary = HoneyText,
    onTertiary = Color.White,
    onBackground = HoneyText,
    onSurface = HoneyText
)

@Composable
fun MadhuMargaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

package com.example.mobile_hw2.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.mobile_hw2.theme.AppTypography

private val LightColors = lightColorScheme(
    primary = RedditOrange,
    background = LightBackground,
    surface = LightSurface
)

private val DarkColors = darkColorScheme(
    primary = RedditOrange,
    background = DarkBackground,
    surface = DarkSurface
)

@Composable
fun RedditTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = AppTypography,
        content = content
    )
}
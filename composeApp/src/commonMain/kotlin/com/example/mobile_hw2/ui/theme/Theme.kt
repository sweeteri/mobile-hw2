package com.example.mobile_hw2.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.example.mobile_hw2.theme.AppTypography

data class ExtendedColorScheme(
    val textFieldBackground: Color,
    val textSecondary: Color,
    val accentOrange: Color
)

val LocalExtendedColors = compositionLocalOf {
    ExtendedColorScheme(
        textFieldBackground = Color.Unspecified,
        textSecondary = Color.Unspecified,
        accentOrange = Color.Unspecified
    )
}

private val LightColors = lightColorScheme(
    primary = RedditOrange,
    background = LightBackground,
    surface = LightSurface,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val DarkColors = darkColorScheme(
    primary = RedditOrange,
    background = DarkBackground,
    surface = DarkSurface,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun RedditTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    val extendedColors = if (darkTheme) {
        ExtendedColorScheme(
            textFieldBackground = DarkTextFieldBackground,
            textSecondary = DarkTextSecondary,
            accentOrange = AccentOrange
        )
    } else {
        ExtendedColorScheme(
            textFieldBackground = LightTextFieldBackground,
            textSecondary = LightTextSecondary,
            accentOrange = AccentOrange
        )
    }

    CompositionLocalProvider(
        LocalExtendedColors provides extendedColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            content = content
        )
    }
}

object RedditTheme {
    val colors: ExtendedColorScheme
        @Composable
        get() = LocalExtendedColors.current
}
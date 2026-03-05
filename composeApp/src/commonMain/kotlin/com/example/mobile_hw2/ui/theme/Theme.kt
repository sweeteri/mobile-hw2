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
    val accent: Color
)

val LocalExtendedColors = compositionLocalOf {
    ExtendedColorScheme(
        textFieldBackground = Color.Unspecified,
        textSecondary = Color.Unspecified,
        accent = Color.Unspecified
    )
}

private val LightColors = lightColorScheme(
    primary = Color.White,
    background = LightBackground,
    surface = LightSurface,

    onPrimary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val DarkColors = darkColorScheme(
    primary = Color.White,
    background = DarkBackground,
    surface = DarkSurface,

    onPrimary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun StepikTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    val extendedColors = if (darkTheme) {
        ExtendedColorScheme(
            textFieldBackground = DarkTextFieldBackground,
            textSecondary = DarkTextSecondary,
            accent = StepikGreen
        )
    } else {
        ExtendedColorScheme(
            textFieldBackground = LightTextFieldBackground,
            textSecondary = LightTextSecondary,
            accent = StepikGreen
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

object StepikTheme {
    val colors: ExtendedColorScheme
        @Composable
        get() = LocalExtendedColors.current
}
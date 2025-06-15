package com.test.ui.theme

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
    primary = SolidBlack,
    onPrimary = SolidWhite,
    secondary = DarkGrey,
    onSecondary = SolidWhite,
    tertiary = CustomGrey,
    onTertiary = SolidWhite,
    background = SolidBlack,
    onBackground = SolidWhite,
    surface = DarkGrey,
    onSurface = SolidWhite,
    error = ErrorColor,
    onError = SolidWhite
)

private val LightColorScheme = lightColorScheme(
    primary = TertiaryBlue,
    onPrimary = SolidBlack,
    secondary = SolidWhite,
    onSecondary = SolidBlack,
    tertiary = CustomGrey,
    onTertiary = SolidWhite,
    background = SolidWhite,
    onBackground = SolidBlack,
    surface = LightGrey,
    onSurface = SolidBlack,
    error = ErrorColor,
    onError = SolidBlack
)

@Composable
fun SynonymssearchtoolTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
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
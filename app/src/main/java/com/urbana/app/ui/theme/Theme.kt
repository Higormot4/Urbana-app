package com.urbana.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(
    primary = UrbanOrange,
    background = UrbanBlack,
    surface = UrbanDarkGray,
    onPrimary = UrbanWhite,
    onBackground = UrbanWhite,
    onSurface = UrbanWhite
)

private val LightColors = lightColorScheme(
    primary = UrbanOrange,
    background = UrbanLightGray,
    surface = UrbanWhite,
    onPrimary = UrbanWhite,
    onBackground = UrbanBlack,
    onSurface = UrbanBlack
)

@Composable
fun URBANATheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content
    )
}
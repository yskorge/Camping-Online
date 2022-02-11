package com.example.android.campingonline.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import com.example.android.campingonline.R

private val DarkColorPalette = darkColors(
    surface = DarkGray,
    onSurface = White,
    primary = Color(0xff1e88e5),
    onPrimary = Black
)

private val LightColorPalette = lightColors(
    surface = White,
    onSurface = Black,
    primary = Color(0xff1e88e5),
    onPrimary = White
)

// TODO  This is still unused and need to be adjusted according to the theme we want for this app
// TODO this is just put here are example of how theme can be done in compose
@Composable
fun BasicsCampingOnlineTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
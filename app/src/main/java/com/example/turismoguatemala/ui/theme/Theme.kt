package com.example.turismoguatemala.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Definición de colores
private val DarkColorPalette = darkColorScheme(
    primary = Color(0xFF4CAF50), // Verde naturaleza
    secondary = Color(0xFF2196F3), // Azul cielo
    background = Color(0xFF1B5E20), // Fondo oscuro
    surface = Color(0xFFFAFAFA),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.Black
)

private val LightColorPalette = lightColorScheme(
    primary = Color(0xFF4CAF50), // Verde naturaleza
    secondary = Color(0xFF2196F3), // Azul cielo
    background = Color(0xFFFAFAFA), // Fondo claro
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun TurismoGuatemalaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
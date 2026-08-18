package com.waysense.app.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = WayPrimary,
    onPrimary = WayOnPrimary,
    primaryContainer = WayPrimaryContainer,
    onPrimaryContainer = WayOnPrimaryContainer,
    secondary = WaySecondary,
    onSecondary = WayOnSecondary,
    secondaryContainer = WaySecondaryContainer,
    onSecondaryContainer = WayOnSecondaryContainer,
    tertiary = WayTertiary,
    onTertiary = WayOnTertiary,
    tertiaryContainer = WayTertiaryContainer,
    onTertiaryContainer = WayOnTertiaryContainer,
    background = WayBackground,
    onBackground = WayOnBackground,
    surface = WaySurface,
    onSurface = WayOnSurface,
    surfaceVariant = WaySurfaceVariant,
    onSurfaceVariant = WayOnSurfaceVariant,
    error = WayError,
    onError = WayOnError,
    errorContainer = WayErrorContainer,
    onErrorContainer = WayOnErrorContainer,
    outline = WayOutline,
    outlineVariant = WayOutlineVariant,
)

private val DarkColorScheme = darkColorScheme(
    primary = WayPrimaryDark,
    onPrimary = WayOnPrimaryDark,
    primaryContainer = WayPrimaryContainerDark,
    onPrimaryContainer = WayOnPrimaryContainerDark,
    secondary = WaySecondaryDark,
    onSecondary = WayOnSecondaryDark,
    secondaryContainer = WaySecondaryContainerDark,
    onSecondaryContainer = WayOnSecondaryContainerDark,
    tertiary = WayTertiaryDark,
    onTertiary = WayOnTertiaryDark,
    tertiaryContainer = WayTertiaryContainerDark,
    onTertiaryContainer = WayOnTertiaryContainerDark,
    background = WayBackgroundDark,
    onBackground = WayOnBackgroundDark,
    surface = WaySurfaceDark,
    onSurface = WayOnSurfaceDark,
    surfaceVariant = WaySurfaceVariantDark,
    onSurfaceVariant = WayOnSurfaceVariantDark,
    error = WayErrorDark,
    onError = WayOnErrorDark,
    errorContainer = WayErrorContainerDark,
    onErrorContainer = WayOnErrorContainerDark,
    outline = WayOutlineDark,
    outlineVariant = WayOutlineVariantDark,
)

@Composable
fun WaySenseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
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
        typography = WaySenseTypography,
        shapes = WaySenseShapes,
        content = content,
    )
}

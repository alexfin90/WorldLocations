package com.softdream.worldlocations.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


// Material 3 color schemes
private val worldLocationsDarkColorScheme = darkColorScheme(
    primary = worldLocationsDarkPrimary,
    onPrimary = worldLocationsDarkOnPrimary,
    primaryContainer = worldLocationsDarkPrimaryContainer,
    onPrimaryContainer = worldLocationsDarkOnPrimaryContainer,
    inversePrimary = worldLocationsDarkPrimaryInverse,
    secondary = worldLocationsDarkSecondary,
    onSecondary = worldLocationsDarkOnSecondary,
    secondaryContainer = worldLocationsDarkSecondaryContainer,
    onSecondaryContainer = worldLocationsDarkOnSecondaryContainer,
    tertiary = worldLocationsDarkTertiary,
    onTertiary = worldLocationsDarkOnTertiary,
    tertiaryContainer = worldLocationsDarkTertiaryContainer,
    onTertiaryContainer = worldLocationsDarkOnTertiaryContainer,
    error = worldLocationsDarkError,
    onError = worldLocationsDarkOnError,
    errorContainer = worldLocationsDarkErrorContainer,
    onErrorContainer = worldLocationsDarkOnErrorContainer,
    background = worldLocationsDarkBackground,
    onBackground = worldLocationsDarkOnBackground,
    surface = worldLocationsDarkSurface,
    onSurface = worldLocationsDarkOnSurface,
    inverseSurface = worldLocationsDarkInverseSurface,
    inverseOnSurface = worldLocationsDarkInverseOnSurface,
    surfaceVariant = worldLocationsDarkSurfaceVariant,
    onSurfaceVariant = worldLocationsDarkOnSurfaceVariant,
    outline = worldLocationsDarkOutline
)

private val worldLocationsLightColorScheme = lightColorScheme(
    primary = worldLocationsLightPrimary,
    onPrimary = worldLocationsLightOnPrimary,
    primaryContainer = worldLocationsLightPrimaryContainer,
    onPrimaryContainer = worldLocationsLightOnPrimaryContainer,
    inversePrimary = worldLocationsLightPrimaryInverse,
    secondary = worldLocationsLightSecondary,
    onSecondary = worldLocationsLightOnSecondary,
    secondaryContainer = worldLocationsLightSecondaryContainer,
    onSecondaryContainer = worldLocationsLightOnSecondaryContainer,
    tertiary = worldLocationsLightTertiary,
    onTertiary = worldLocationsLightOnTertiary,
    tertiaryContainer = worldLocationsLightTertiaryContainer,
    onTertiaryContainer = worldLocationsLightOnTertiaryContainer,
    error = worldLocationsLightError,
    onError = worldLocationsLightOnError,
    errorContainer = worldLocationsLightErrorContainer,
    onErrorContainer = worldLocationsLightOnErrorContainer,
    background = worldLocationsLightBackground,
    onBackground = worldLocationsLightOnBackground,
    surface = worldLocationsLightSurface,
    onSurface = worldLocationsLightOnSurface,
    inverseSurface = worldLocationsLightInverseSurface,
    inverseOnSurface = worldLocationsLightInverseOnSurface,
    surfaceVariant = worldLocationsLightSurfaceVariant,
    onSurfaceVariant = worldLocationsLightOnSurfaceVariant,
    outline = worldLocationsLightOutline
)

@Composable
fun WorldLocationsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val worldLocationsColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> worldLocationsDarkColorScheme
        else -> worldLocationsLightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = worldLocationsColorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = worldLocationsColorScheme,
        typography = worldLocationsTypography,
        shapes = shapes,
        content = content
    )
}


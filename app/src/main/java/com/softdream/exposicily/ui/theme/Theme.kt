package com.softdream.exposicily.ui.theme

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
private val expoSicilyDarkColorScheme = darkColorScheme(
    primary = expoSicilyDarkPrimary,
    onPrimary = expoSicilyDarkOnPrimary,
    primaryContainer = expoSicilyDarkPrimaryContainer,
    onPrimaryContainer = expoSicilyDarkOnPrimaryContainer,
    inversePrimary = expoSicilyDarkPrimaryInverse,
    secondary = expoSicilyDarkSecondary,
    onSecondary = expoSicilyDarkOnSecondary,
    secondaryContainer = expoSicilyDarkSecondaryContainer,
    onSecondaryContainer = expoSicilyDarkOnSecondaryContainer,
    tertiary = expoSicilyDarkTertiary,
    onTertiary = expoSicilyDarkOnTertiary,
    tertiaryContainer = expoSicilyDarkTertiaryContainer,
    onTertiaryContainer = expoSicilyDarkOnTertiaryContainer,
    error = expoSicilyDarkError,
    onError = expoSicilyDarkOnError,
    errorContainer = expoSicilyDarkErrorContainer,
    onErrorContainer = expoSicilyDarkOnErrorContainer,
    background = expoSicilyDarkBackground,
    onBackground = expoSicilyDarkOnBackground,
    surface = expoSicilyDarkSurface,
    onSurface = expoSicilyDarkOnSurface,
    inverseSurface = expoSicilyDarkInverseSurface,
    inverseOnSurface = expoSicilyDarkInverseOnSurface,
    surfaceVariant = expoSicilyDarkSurfaceVariant,
    onSurfaceVariant = expoSicilyDarkOnSurfaceVariant,
    outline = expoSicilyDarkOutline
)

private val expoSicilyLightColorScheme = lightColorScheme(
    primary = expoSicilyLightPrimary,
    onPrimary = expoSicilyLightOnPrimary,
    primaryContainer = expoSicilyLightPrimaryContainer,
    onPrimaryContainer = expoSicilyLightOnPrimaryContainer,
    inversePrimary = expoSicilyLightPrimaryInverse,
    secondary = expoSicilyLightSecondary,
    onSecondary = expoSicilyLightOnSecondary,
    secondaryContainer = expoSicilyLightSecondaryContainer,
    onSecondaryContainer = expoSicilyLightOnSecondaryContainer,
    tertiary = expoSicilyLightTertiary,
    onTertiary = expoSicilyLightOnTertiary,
    tertiaryContainer = expoSicilyLightTertiaryContainer,
    onTertiaryContainer = expoSicilyLightOnTertiaryContainer,
    error = expoSicilyLightError,
    onError = expoSicilyLightOnError,
    errorContainer = expoSicilyLightErrorContainer,
    onErrorContainer = expoSicilyLightOnErrorContainer,
    background = expoSicilyLightBackground,
    onBackground = expoSicilyLightOnBackground,
    surface = expoSicilyLightSurface,
    onSurface = expoSicilyLightOnSurface,
    inverseSurface = expoSicilyLightInverseSurface,
    inverseOnSurface = expoSicilyLightInverseOnSurface,
    surfaceVariant = expoSicilyLightSurfaceVariant,
    onSurfaceVariant = expoSicilyLightOnSurfaceVariant,
    outline = expoSicilyLightOutline
)

@Composable
fun ExpoSicilyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val expoSicilyColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> expoSicilyDarkColorScheme
        else -> expoSicilyLightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = expoSicilyColorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = expoSicilyColorScheme,
        typography = expoSicilyTypography,
        shapes = shapes,
        content = content
    )
}


package com.example.amanshop.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.window.core.layout.WindowWidthSizeClass

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = customPrimaryPink,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    primaryContainer = customPrimaryContainerPink,
    onPrimaryContainer = customOnPrimaryContainerPink

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

var Dimens = ScreensDimen.OtherScreenDimens

@Composable
fun AmanTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = LightColorScheme
    /*
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

     */

    val window = currentWindowAdaptiveInfo().windowSizeClass
    val config = LocalConfiguration.current

    var typography = OtherScreenTypography

    when(window.windowWidthSizeClass){
        WindowWidthSizeClass.COMPACT ->{

            if (config.screenWidthDp <= 360){
                typography = SmallScreenTypography
                Dimens = ScreensDimen.SmallCompactScreenDimens
            }else if(config.screenWidthDp < 480){
                typography = MediumScreenTypography
                Dimens = ScreensDimen.MediumCompactScreenDimens
            }else{
                typography = LargeScreenTypography
                Dimens = ScreensDimen.LargeCompactScreenDimens
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}
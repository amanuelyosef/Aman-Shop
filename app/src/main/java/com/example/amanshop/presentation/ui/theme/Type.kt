package com.example.amanshop.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.amanshop.R

val nunitoFontFamily = FontFamily(
    Font(R.font.nunito_black, FontWeight.Black),
    Font(R.font.nunito_blackitalic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.nunito_bold, FontWeight.Bold),
    Font(R.font.nunito_bolditalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.nunito_extrabold, FontWeight.ExtraBold),
    Font(R.font.nunito_extrabolditalic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.nunito_extralight, FontWeight.ExtraLight),
    Font(R.font.nunito_extralightitalic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.nunito_light, FontWeight.Light),
    Font(R.font.nunito_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.nunito_lightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.nunito_medium,  FontWeight.Medium),
    Font(R.font.nunito_mediumitalic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.nunito_regular,  FontWeight.Normal),
    Font(R.font.nunito_semibold, FontWeight.SemiBold),
    Font(R.font.nunito_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),
    
)

// Set of Material typography styles to start with


data class TypographySize(
    val fontSize: TextUnit,
    val lineHeight: TextUnit,
    val letterSpacing: TextUnit
)

val largeScreenTypographySizes = mapOf(
    "bodySmall" to TypographySize(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.25.sp
    ),
    "bodyMedium" to TypographySize(
        fontSize = 18.sp,
        lineHeight = 27.sp,
        letterSpacing = 0.25.sp
    ),
    "bodyLarge" to TypographySize(
        fontSize = 22.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.25.sp
    ),
    "labelSmall" to TypographySize(
        fontSize = 14.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.2.sp
    ),
    "labelMedium" to TypographySize(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.2.sp
    ),
    "labelLarge" to TypographySize(
        fontSize = 18.sp,
        lineHeight = 27.sp,
        letterSpacing = 0.2.sp
    ),
    "titleSmall" to TypographySize(
        fontSize = 20.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.15.sp
    ),
    "titleMedium" to TypographySize(
        fontSize = 22.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.15.sp
    ),
    "titleLarge" to TypographySize(
        fontSize = 24.sp,
        lineHeight = 42.sp,
        letterSpacing = 0.15.sp
    ),
    "displaySmall" to TypographySize(
        fontSize = 24.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.1.sp
    ),
    "displayMedium" to TypographySize(
        fontSize = 28.sp,
        lineHeight = 42.sp,
        letterSpacing = 0.1.sp
    ),
    "displayLarge" to TypographySize(
        fontSize = 34.sp,
        lineHeight = 51.sp,
        letterSpacing = 0.1.sp
    ),
    "headlineSmall" to TypographySize(
        fontSize = 18.sp,
        lineHeight = 27.sp,
        letterSpacing = 0.2.sp
    ),
    "headlineMedium" to TypographySize(
        fontSize = 24.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.2.sp
    ),
    "headlineLarge" to TypographySize(
        fontSize = 30.sp,
        lineHeight = 45.sp,
        letterSpacing = 0.2.sp
    )
)

val mediumScreenTypographySizes = mapOf(
    "bodySmall" to TypographySize(14.sp,15.sp,0.2.sp),
    "bodyMedium" to TypographySize(16.sp,18.sp,0.25.sp),
    "bodyLarge" to TypographySize(20.sp,20.sp,0.25.sp),
    "labelSmall" to TypographySize(10.sp,18.sp,0.2.sp),
    "labelMedium" to TypographySize(12.sp,21.sp,0.2.sp),
    "labelLarge" to TypographySize(14.sp,24.sp,0.2.sp),
    "titleSmall" to TypographySize(18.sp, 20.sp, 0.15.sp),
    "titleMedium" to TypographySize(20.sp, 22.sp, 0.15.sp),
    "titleLarge" to TypographySize(22.sp, 24.sp, 0.15.sp),
    "displaySmall" to TypographySize(22.sp,30.sp,0.1.sp),
    "displayMedium" to TypographySize(26.sp,36.sp,0.1.sp),
    "displayLarge" to TypographySize(30.sp,48.sp,0.1.sp),
    "headlineSmall" to TypographySize(16.sp, 24.sp, 0.2.sp),
    "headlineMedium" to TypographySize(22.sp, 33.sp,0.2.sp),
    "headlineLarge" to TypographySize(26.sp, 42.sp,0.2.sp)
    )

val smallScreenTypographySizes = mapOf(
    "bodySmall" to TypographySize(12.sp, 14.sp, 0.25.sp),
    "bodyMedium" to TypographySize(14.sp, 16.sp, 0.25.sp),
    "bodyLarge" to TypographySize(18.sp, 18.sp, 0.25.sp),
    "labelSmall" to TypographySize(9.sp, 15.sp, 0.2.sp),
    "labelMedium" to TypographySize(11.sp, 18.sp, 0.2.sp),
    "labelLarge" to TypographySize(13.sp, 21.sp, 0.2.sp),
    "titleSmall" to TypographySize(16.sp, 24.sp, 0.15.sp),
    "titleMedium" to TypographySize(18.sp, 27.sp, 0.15.sp),
    "titleLarge" to TypographySize(20.sp, 30.sp, 0.15.sp),
    "displaySmall" to TypographySize(20.sp, 27.sp, 0.1.sp),
    "displayMedium" to TypographySize(24.sp, 30.sp, 0.1.sp),
    "displayLarge" to TypographySize(24.sp, 36.sp,0.1.sp),
    "headlineLarge" to TypographySize(24.sp, 21.sp,0.2.sp),
    "headlineMedium" to TypographySize(20.sp, 24.sp,0.2.sp),
    "headlineSmall" to TypographySize(14.sp, 27.sp, 0.2.sp)
)

val otherScreenTypographySizes = mapOf(
    "bodySmall" to TypographySize(TextUnit.Unspecified, TextUnit.Unspecified, TextUnit.Unspecified),
    "bodyMedium" to TypographySize(TextUnit.Unspecified, TextUnit.Unspecified, TextUnit.Unspecified),
    "bodyLarge" to TypographySize(TextUnit.Unspecified, TextUnit.Unspecified, TextUnit.Unspecified),
    "labelSmall" to TypographySize(TextUnit.Unspecified, TextUnit.Unspecified, TextUnit.Unspecified),
    "labelMedium" to TypographySize(TextUnit.Unspecified, TextUnit.Unspecified, TextUnit.Unspecified),
    "labelLarge" to TypographySize(TextUnit.Unspecified, TextUnit.Unspecified, TextUnit.Unspecified),
    "titleSmall" to TypographySize(TextUnit.Unspecified, TextUnit.Unspecified, TextUnit.Unspecified),
    "titleMedium" to TypographySize(TextUnit.Unspecified, TextUnit.Unspecified, TextUnit.Unspecified),
    "titleLarge" to TypographySize(TextUnit.Unspecified, TextUnit.Unspecified, TextUnit.Unspecified),
    "displaySmall" to TypographySize(TextUnit.Unspecified, TextUnit.Unspecified, TextUnit.Unspecified),
    "displayMedium" to TypographySize(TextUnit.Unspecified, TextUnit.Unspecified, TextUnit.Unspecified),
    "displayLarge" to TypographySize(38.sp, TextUnit.Unspecified, TextUnit.Unspecified),
    "headlineLarge" to TypographySize(TextUnit.Unspecified, TextUnit.Unspecified, TextUnit.Unspecified),
    "headlineMedium" to TypographySize(TextUnit.Unspecified, TextUnit.Unspecified, TextUnit.Unspecified),
    "headlineSmall" to TypographySize(TextUnit.Unspecified, TextUnit.Unspecified, TextUnit.Unspecified)
)

val LargeScreenTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = nunitoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = largeScreenTypographySizes["bodyLarge"]!!.fontSize,
        lineHeight = largeScreenTypographySizes["bodyLarge"]!!.lineHeight,
        letterSpacing = largeScreenTypographySizes["bodyLarge"]!!.letterSpacing
    ),
    bodyMedium = TextStyle(
        fontFamily = nunitoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = largeScreenTypographySizes["bodyMedium"]!!.fontSize,
        lineHeight = largeScreenTypographySizes["bodyMedium"]!!.lineHeight,
        letterSpacing = largeScreenTypographySizes["bodyMedium"]!!.letterSpacing
    ),
    bodySmall = TextStyle(
        fontFamily = nunitoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = largeScreenTypographySizes["bodySmall"]!!.fontSize,
        lineHeight = largeScreenTypographySizes["bodySmall"]!!.lineHeight,
        letterSpacing = largeScreenTypographySizes["bodySmall"]!!.letterSpacing
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = largeScreenTypographySizes["labelLarge"]!!.fontSize,
        lineHeight = largeScreenTypographySizes["labelLarge"]!!.lineHeight,
        letterSpacing = largeScreenTypographySizes["labelLarge"]!!.letterSpacing
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = largeScreenTypographySizes["labelMedium"]!!.fontSize,
        lineHeight = largeScreenTypographySizes["labelMedium"]!!.lineHeight,
        letterSpacing = largeScreenTypographySizes["labelMedium"]!!.letterSpacing
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = largeScreenTypographySizes["labelSmall"]!!.fontSize,
        lineHeight = largeScreenTypographySizes["labelSmall"]!!.lineHeight,
        letterSpacing = largeScreenTypographySizes["labelSmall"]!!.letterSpacing
    ),
    displayLarge = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = largeScreenTypographySizes["displayLarge"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        letterSpacing = largeScreenTypographySizes["displayLarge"]!!.letterSpacing
    ),
    displayMedium = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = largeScreenTypographySizes["displayMedium"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        letterSpacing = largeScreenTypographySizes["displayMedium"]!!.letterSpacing
    ),
    displaySmall = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = largeScreenTypographySizes["displaySmall"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        letterSpacing = largeScreenTypographySizes["displaySmall"]!!.letterSpacing
    ),
    headlineLarge = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = largeScreenTypographySizes["headlineLarge"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        lineHeight = largeScreenTypographySizes["headlineLarge"]!!.lineHeight
    ),
    headlineMedium = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = largeScreenTypographySizes["headlineMedium"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        lineHeight = largeScreenTypographySizes["headlineMedium"]!!.lineHeight
    ),
    headlineSmall = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = largeScreenTypographySizes["headlineSmall"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        lineHeight = largeScreenTypographySizes["headlineSmall"]!!.lineHeight
    )
)

val OtherScreenTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = nunitoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = otherScreenTypographySizes["bodyLarge"]!!.fontSize,
        lineHeight = otherScreenTypographySizes["bodyLarge"]!!.lineHeight,
        letterSpacing = otherScreenTypographySizes["bodyLarge"]!!.letterSpacing
    ),
    bodyMedium = TextStyle(
        fontFamily = nunitoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = otherScreenTypographySizes["bodyMedium"]!!.fontSize,
        lineHeight = otherScreenTypographySizes["bodyMedium"]!!.lineHeight,
        letterSpacing = otherScreenTypographySizes["bodyMedium"]!!.letterSpacing
    ),
    bodySmall = TextStyle(
        fontFamily = nunitoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = otherScreenTypographySizes["bodySmall"]!!.fontSize,
        lineHeight = otherScreenTypographySizes["bodySmall"]!!.lineHeight,
        letterSpacing = otherScreenTypographySizes["bodySmall"]!!.letterSpacing
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = otherScreenTypographySizes["labelLarge"]!!.fontSize,
        lineHeight = otherScreenTypographySizes["labelLarge"]!!.lineHeight,
        letterSpacing = otherScreenTypographySizes["labelLarge"]!!.letterSpacing
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = otherScreenTypographySizes["labelMedium"]!!.fontSize,
        lineHeight = otherScreenTypographySizes["labelMedium"]!!.lineHeight,
        letterSpacing = otherScreenTypographySizes["labelMedium"]!!.letterSpacing
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = otherScreenTypographySizes["labelSmall"]!!.fontSize,
        lineHeight = otherScreenTypographySizes["labelSmall"]!!.lineHeight,
        letterSpacing = otherScreenTypographySizes["labelSmall"]!!.letterSpacing
    ),
    displayLarge = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = otherScreenTypographySizes["displayLarge"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        letterSpacing = otherScreenTypographySizes["displayLarge"]!!.letterSpacing
    ),
    displayMedium = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = otherScreenTypographySizes["displayMedium"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        letterSpacing = otherScreenTypographySizes["displayMedium"]!!.letterSpacing
    ),
    displaySmall = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = otherScreenTypographySizes["displaySmall"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        letterSpacing = otherScreenTypographySizes["displaySmall"]!!.letterSpacing
    ),
    headlineLarge = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = otherScreenTypographySizes["headlineLarge"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        lineHeight = otherScreenTypographySizes["headlineLarge"]!!.lineHeight
    ),
    headlineMedium = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = otherScreenTypographySizes["headlineMedium"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        lineHeight = otherScreenTypographySizes["headlineMedium"]!!.lineHeight
    ),
    headlineSmall = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = otherScreenTypographySizes["headlineSmall"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        lineHeight = otherScreenTypographySizes["headlineSmall"]!!.lineHeight
    )
)

val MediumScreenTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = nunitoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = mediumScreenTypographySizes["bodyLarge"]!!.fontSize,
        lineHeight = mediumScreenTypographySizes["bodyLarge"]!!.lineHeight,
        letterSpacing = mediumScreenTypographySizes["bodyLarge"]!!.letterSpacing
    ),
    bodyMedium = TextStyle(
        fontFamily = nunitoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = mediumScreenTypographySizes["bodyMedium"]!!.fontSize,
        lineHeight = mediumScreenTypographySizes["bodyMedium"]!!.lineHeight,
        letterSpacing = mediumScreenTypographySizes["bodyMedium"]!!.letterSpacing
    ),
    bodySmall = TextStyle(
        fontFamily = nunitoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = mediumScreenTypographySizes["bodySmall"]!!.fontSize,
        lineHeight = mediumScreenTypographySizes["bodySmall"]!!.lineHeight,
        letterSpacing = mediumScreenTypographySizes["bodySmall"]!!.letterSpacing
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = mediumScreenTypographySizes["labelLarge"]!!.fontSize,
        lineHeight = mediumScreenTypographySizes["labelLarge"]!!.lineHeight,
        letterSpacing = mediumScreenTypographySizes["labelLarge"]!!.letterSpacing
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = mediumScreenTypographySizes["labelMedium"]!!.fontSize,
        lineHeight = mediumScreenTypographySizes["labelMedium"]!!.lineHeight,
        letterSpacing = mediumScreenTypographySizes["labelMedium"]!!.letterSpacing
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = mediumScreenTypographySizes["labelSmall"]!!.fontSize,
        lineHeight = mediumScreenTypographySizes["labelSmall"]!!.lineHeight,
        letterSpacing = mediumScreenTypographySizes["labelSmall"]!!.letterSpacing
    ),
    displayLarge = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = mediumScreenTypographySizes["displayLarge"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        letterSpacing = mediumScreenTypographySizes["displayLarge"]!!.letterSpacing
    ),
    displayMedium = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = mediumScreenTypographySizes["displayMedium"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        letterSpacing = mediumScreenTypographySizes["displayMedium"]!!.letterSpacing
    ),
    displaySmall = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = mediumScreenTypographySizes["displaySmall"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        letterSpacing = mediumScreenTypographySizes["displaySmall"]!!.letterSpacing
    ),
    headlineLarge = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = mediumScreenTypographySizes["headlineLarge"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        lineHeight = mediumScreenTypographySizes["headlineLarge"]!!.lineHeight
    ),
    headlineMedium = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = mediumScreenTypographySizes["headlineMedium"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        lineHeight = mediumScreenTypographySizes["headlineMedium"]!!.lineHeight
    ),
    headlineSmall = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = mediumScreenTypographySizes["headlineSmall"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        lineHeight = mediumScreenTypographySizes["headlineSmall"]!!.lineHeight
    )
)

val SmallScreenTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = nunitoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = smallScreenTypographySizes["bodyLarge"]!!.fontSize,
        lineHeight = smallScreenTypographySizes["bodyLarge"]!!.lineHeight,
        letterSpacing = smallScreenTypographySizes["bodyLarge"]!!.letterSpacing
    ),
    bodyMedium = TextStyle(
        fontFamily = nunitoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = smallScreenTypographySizes["bodyMedium"]!!.fontSize,
        lineHeight = smallScreenTypographySizes["bodyMedium"]!!.lineHeight,
        letterSpacing = smallScreenTypographySizes["bodyMedium"]!!.letterSpacing
    ),
    bodySmall = TextStyle(
        fontFamily = nunitoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = smallScreenTypographySizes["bodySmall"]!!.fontSize,
        lineHeight = smallScreenTypographySizes["bodySmall"]!!.lineHeight,
        letterSpacing = smallScreenTypographySizes["bodySmall"]!!.letterSpacing
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = smallScreenTypographySizes["labelLarge"]!!.fontSize,
        lineHeight = smallScreenTypographySizes["labelLarge"]!!.lineHeight,
        letterSpacing = smallScreenTypographySizes["labelLarge"]!!.letterSpacing
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = smallScreenTypographySizes["labelMedium"]!!.fontSize,
        lineHeight = smallScreenTypographySizes["labelMedium"]!!.lineHeight,
        letterSpacing = smallScreenTypographySizes["labelMedium"]!!.letterSpacing
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = smallScreenTypographySizes["labelSmall"]!!.fontSize,
        lineHeight = smallScreenTypographySizes["labelSmall"]!!.lineHeight,
        letterSpacing = smallScreenTypographySizes["labelSmall"]!!.letterSpacing
    ),
    displayLarge = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = smallScreenTypographySizes["displayLarge"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        letterSpacing = smallScreenTypographySizes["displayLarge"]!!.letterSpacing
    ),
    displayMedium = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = smallScreenTypographySizes["displayMedium"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        letterSpacing = smallScreenTypographySizes["displayMedium"]!!.letterSpacing
    ),
    displaySmall = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = smallScreenTypographySizes["displaySmall"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        letterSpacing = smallScreenTypographySizes["displaySmall"]!!.letterSpacing
    ),
    headlineLarge = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = smallScreenTypographySizes["headlineLarge"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        lineHeight = smallScreenTypographySizes["headlineLarge"]!!.lineHeight
    ),
    headlineMedium = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = smallScreenTypographySizes["headlineMedium"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        lineHeight = smallScreenTypographySizes["headlineMedium"]!!.lineHeight
    ),
    headlineSmall = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = smallScreenTypographySizes["headlineSmall"]!!.fontSize,
        fontWeight = FontWeight.Normal,
        lineHeight = smallScreenTypographySizes["headlineSmall"]!!.lineHeight
    )
)


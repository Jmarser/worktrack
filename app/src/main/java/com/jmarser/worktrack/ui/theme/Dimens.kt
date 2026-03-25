package com.jmarser.worktrack.ui.theme

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Project: WorkTrack
 * File: Dimens.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 25/03/2026
 */
 
data class AppDimens(
    val borderExtraSmall: Dp = 1.dp,
    val borderSmall: Dp = 2.dp,
    val borderNormal: Dp = 3.dp,
    val buttonHeightNormal: Dp = 56.dp,
    val iconSizeExtraSmall: Dp = 16.dp,
    val iconSizeSmall: Dp = 24.dp,
    val iconSizeNormal: Dp = 32.dp,
    val iconSizeLarge: Dp = 40.dp,
    val iconSizeExtralarge: Dp = 56.dp,
    val paddingTiny: Dp = 4.dp,
    val paddingSmall: Dp = 8.dp,
    val paddingNormal: Dp = 12.dp,
    val paddingMedium: Dp = 16.dp,
    val paddingLarge: Dp = 20.dp,
    val paddingXL: Dp = 28.dp,
    val spacerSmall: Dp = 4.dp,
    val spacerNormal: Dp = 8.dp,
    val spacerMedium: Dp = 16.dp,
    val spacerLarge: Dp = 24.dp,
    val spacerXXL: Dp = 32.dp,
    val roundedShapePercent50: CornerSize = CornerSize(50),
    val roundedShapePercent25: CornerSize = CornerSize(25),
    val cardElevationSmall: Dp = 1.dp,
    val cardElevationNormal: Dp = 3.dp,
    val cardElevationLarge: Dp = 6.dp,
    val logoSize: Dp = 250.dp
)

val LocalDimens = staticCompositionLocalOf { AppDimens() }
val appDimens: AppDimens @Composable get() = LocalDimens.current
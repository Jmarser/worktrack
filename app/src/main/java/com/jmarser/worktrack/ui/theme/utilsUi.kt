package com.jmarser.worktrack.ui.theme

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Project: WorkTrack
 * File: utilsUi.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/04/2026
 */

fun Modifier.shimmerEffect(): Modifier = composed {
    val transition = rememberInfiniteTransition("shimmer")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer_rect"
    )
    val shimmerColors = listOf(
        Color.LightGray.copy(.8f),
        Color.LightGray.copy(.2f),
        Color.LightGray.copy(.8f),
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim, y = translateAnim)
    )
    this.background(brush)
}
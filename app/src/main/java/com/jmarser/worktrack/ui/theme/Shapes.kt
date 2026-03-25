package com.jmarser.worktrack.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Project: WorkTrack
 * File: Shapes.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 25/03/2026
 */
 
val shapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp), // tooltips
    small = RoundedCornerShape(8.dp), // Botones pequeños, campos de texto
    medium = RoundedCornerShape(16.dp), // Card, Diálogo
    large = RoundedCornerShape(24.dp), // Navigation Drawer
    extraLarge = RoundedCornerShape(32.dp), // Botones de accion flotante
)
package com.jmarser.worktrack.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Project: WorkTrack
 * File: Route.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/03/2026
 */

@Serializable
sealed interface Route: NavKey {

    @Serializable
    data object CompanyList: Route
}
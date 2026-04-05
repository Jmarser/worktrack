package com.jmarser.worktrack.presentation.createCompany.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jmarser.worktrack.R

/**
 * Project: WorkTrack
 * File: CreateCompanyEffect.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/04/2026
 */

sealed interface CreateCompanyEffect {
    object NavigateToBack: CreateCompanyEffect
    object NavigateToSettings: CreateCompanyEffect
    data class ShowMessage (@StringRes val resId: Int): CreateCompanyEffect
}


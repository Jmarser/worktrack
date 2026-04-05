package com.jmarser.worktrack.presentation.createCompany.ui

/**
 * Project: WorkTrack
 * File: CreateCompanyEffect.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/04/2026
 */

sealed interface CreateCompanyEffect {
    object NavigateToBack: CreateCompanyEffect
    object NavigateToSettings: CreateCompanyEffect
    data class ShowMessage(val message: String): CreateCompanyEffect
}
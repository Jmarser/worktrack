package com.jmarser.worktrack.presentation.companyList.ui

import androidx.annotation.StringRes

/**
 * Project: WorkTrack
 * File: CompanyListEffect.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 31/03/2026
 */

sealed interface CompanyListEffect {

    data class NavigateToDetailsCompany(val companyId: Long): CompanyListEffect
    data class NavigateToEditCompany(val companyId: Long): CompanyListEffect
    object NavigateToCreateCompany: CompanyListEffect
    data class ShowMessage(@StringRes val resId: Int): CompanyListEffect
}
package com.jmarser.worktrack.presentation.companyList.ui

import com.jmarser.worktrack.domain.model.CompanySummary

/**
 * Project: WorkTrack
 * File: CompanyListEvent.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 31/03/2026
 */

sealed interface CompanyListEvent {

    object onClickCreateCompany: CompanyListEvent
    object onRetry: CompanyListEvent
    data class onClickEditCompany(val companyId: Long): CompanyListEvent
    data class onClickDeleteCompany(val companyId: Long): CompanyListEvent
    data class onClickNavigateToDetails(val companyId: Long): CompanyListEvent
    data class ToggleDeleteDialogState(val companySummary: CompanySummary?): CompanyListEvent
}
package com.jmarser.worktrack.presentation.companyList.ui

/**
 * Project: WorkTrack
 * File: CompanyListEvent.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 31/03/2026
 */

sealed interface CompanyListEvent {

    object onClickCreateCompany: CompanyListEvent
    data class onClickEditCompany(val companyId: Long): CompanyListEvent
    data class onClickDeleteCompany(val companyId: Long): CompanyListEvent
    data class onClickNavigateToDetails(val companyId: Long): CompanyListEvent
    data class ShowSnackbar(val message: String): CompanyListEvent
}
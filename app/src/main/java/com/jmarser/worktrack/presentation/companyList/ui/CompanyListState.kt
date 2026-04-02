package com.jmarser.worktrack.presentation.companyList.ui

import com.jmarser.worktrack.presentation.companyList.model.HomeUiModel

/**
 * Project: WorkTrack
 * File: CompanyListState.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 31/03/2026
 */

sealed class CompanyListState {

    object Idle: CompanyListState()
    object Loading: CompanyListState()
    object Empty: CompanyListState()
    data class Success(
        val data: HomeUiModel
    ): CompanyListState()
    data class Failure(val message: String): CompanyListState()
}
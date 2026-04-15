package com.jmarser.worktrack.presentation.companyDetails.ui

import com.jmarser.worktrack.core.error.ValidationError
import com.jmarser.worktrack.domain.model.CompanyComplete

/**
 * Project: WorkTrack
 * File: CompanyDetailsState.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 11/04/2026
 */

sealed class CompanyDetailsState {

    object Idle: CompanyDetailsState()
    object Loading: CompanyDetailsState()
    object Empty: CompanyDetailsState()
    data class Success(
        val data: CompanyComplete,
        val totalDays: Int,
        val totalDaysPending: Int,
        val totalPaid: Double,
        val totalPending: Double
    ): CompanyDetailsState()
    data class Failure(val message: ValidationError): CompanyDetailsState()
}
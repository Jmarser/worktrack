package com.jmarser.worktrack.domain.mapper

import com.jmarser.worktrack.domain.model.HomeData
import com.jmarser.worktrack.presentation.companyList.model.HomeUiModel
import java.util.Locale

/**
 * Project: WorkTrack
 * File: HomeDataMapper.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/03/2026
 */

fun HomeData.toUiModel(): HomeUiModel{
    return HomeUiModel(
        companies = this.companies,
        totalCompanies = "${this.totalCompanies}",
        globalPendingDays = "${this.totalGlobalPendingDays}",
        totalPendingAmount = String.format(Locale.getDefault(), "%.2f", this.totalGlobalPendingAmount)
    )
}
package com.jmarser.worktrack.domain.model

data class HomeData(
    val companies: List<CompanySummary>,
    val totalCompanies: Int,
    val totalGlobalPendingDays: Int,
    val totalGlobalPendingAmount: Double
)

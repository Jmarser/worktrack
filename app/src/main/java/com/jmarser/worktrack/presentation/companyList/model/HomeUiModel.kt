package com.jmarser.worktrack.presentation.companyList.model

import com.jmarser.worktrack.domain.model.CompanySummary

data class HomeUiModel(
    val companies: List<CompanySummary>,
    val totalCompanies: String,
    val globalPendingDays: String,
    val totalPendingAmount: String
)

// Datos de prueba para el listado
val mockCompanies = listOf(
    CompanySummary(
        id = 1,
        name = "Neclima S.L.",
        totalDaysWorked = 25,
        daysPending = 3,
        amountPaid = 450.0
    ),
    CompanySummary(id = 2, name = "Construcciones J.M.", totalDaysWorked = 10, daysPending = 0, amountPaid = 0.0),
    CompanySummary(id = 3, name = "Instalaciones Rápidas", totalDaysWorked = 42, daysPending = 15, amountPaid = 2250.0),
    CompanySummary(id = 4, name = "Servicios Logísticos", totalDaysWorked = 5, daysPending = 1, amountPaid = 120.0)
)

// Modelo completo para la UI
val mockHomeUiState = HomeUiModel(
    companies = mockCompanies,
    totalCompanies = "4",
    globalPendingDays = "19",
    totalPendingAmount = "2820.00"
)

val mockCompanySummary = CompanySummary(
    id = 1,
    name = "Neclima S.L.",
    totalDaysWorked = 25,
    daysPending = 0,
    amountPaid = 450.0
)

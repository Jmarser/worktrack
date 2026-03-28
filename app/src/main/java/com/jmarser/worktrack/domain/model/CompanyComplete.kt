package com.jmarser.worktrack.domain.model

data class CompanyComplete(
    val company: Company,
    val workDays: List<WorkDayComplete>? = emptyList()
)

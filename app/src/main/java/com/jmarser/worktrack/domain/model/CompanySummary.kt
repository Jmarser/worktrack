package com.jmarser.worktrack.domain.model

data class CompanySummary(
    val id: Long,
    val name: String,
    val totalDaysWorked: Int,
    val daysPending: Int
)

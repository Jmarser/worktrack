package com.jmarser.worktrack.data.local.database.model

data class CompanySummaryEntity(
    val id: Long,
    val name: String,
    val totalDaysWorked: Int,
    val daysPending: Int
)

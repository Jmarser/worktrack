package com.jmarser.worktrack.domain.model

data class WorkDayComplete(
    val workDay: WorkDay,
    val locations: List<WorkLocation> = emptyList(),
    val expanses: List<Expanse> = emptyList()
)

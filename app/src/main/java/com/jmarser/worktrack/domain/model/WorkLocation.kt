package com.jmarser.worktrack.domain.model

data class WorkLocation(
    val id: Long = 0,
    val workDayId: Long,
    val name: String,
    val description: String? = null
)

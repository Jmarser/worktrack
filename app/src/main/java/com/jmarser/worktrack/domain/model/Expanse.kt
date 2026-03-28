package com.jmarser.worktrack.domain.model

data class Expanse(
    val id: Long = 0,
    val workDayId: Long,
    val description: String,
    val amount: Double,
    val isPaid: Boolean = false
)

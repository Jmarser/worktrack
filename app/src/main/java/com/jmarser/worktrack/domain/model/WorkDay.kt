package com.jmarser.worktrack.domain.model

import java.util.Date

data class WorkDay(
    val id: Long = 0,
    val companyId: Long,
    val date: Date,
    val isHalfTime: Boolean,
    val amountPaid: Double,
    val isPaid: Boolean = false,
    val description: String? = null
)

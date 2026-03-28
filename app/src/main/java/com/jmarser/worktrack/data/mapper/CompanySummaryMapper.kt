package com.jmarser.worktrack.data.mapper

import com.jmarser.worktrack.data.local.database.model.CompanySummaryEntity
import com.jmarser.worktrack.domain.model.CompanySummary

/**
 * Project: WorkTrack
 * File: CompanySummaryMapper.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

fun CompanySummaryEntity.toDomain(): CompanySummary{
    return CompanySummary(
        id = this.id,
        name = this.name,
        totalDaysWorked = this.totalDaysWorked,
        daysPending = this.daysPending
    )
}
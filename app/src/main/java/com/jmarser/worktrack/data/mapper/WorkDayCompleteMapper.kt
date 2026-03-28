package com.jmarser.worktrack.data.mapper

import com.jmarser.worktrack.data.local.database.model.WorkDayWithDetails
import com.jmarser.worktrack.domain.model.WorkDayComplete

/**
 * Project: WorkTrack
 * File: WorkDayCompleteMapper.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

fun WorkDayWithDetails.toDomain(): WorkDayComplete{
    return WorkDayComplete(
        workDay = this.workDay.toDomain(),
        locations = this.locations.map { it.toDomain() },
        expanses = this.expanses.map { it.toDomain() }
    )
}
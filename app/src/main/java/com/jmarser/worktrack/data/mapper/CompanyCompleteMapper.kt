package com.jmarser.worktrack.data.mapper

import com.jmarser.worktrack.data.local.database.model.CompanyWithWorkDays
import com.jmarser.worktrack.domain.model.CompanyComplete

/**
 * Project: WorkTrack
 * File: CompanyCompleteMapper.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

fun CompanyWithWorkDays.toDomain(): CompanyComplete{
    return CompanyComplete(
        company = this.company.toDomain(),
        workDays = this.workDays.map { it.toDomain() }
    )
}
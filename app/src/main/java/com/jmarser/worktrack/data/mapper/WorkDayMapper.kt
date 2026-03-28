package com.jmarser.worktrack.data.mapper

import com.jmarser.worktrack.data.local.database.entity.WorkDayEntity
import com.jmarser.worktrack.domain.model.WorkDay
import java.util.Date

/**
 * Project: WorkTrack
 * File: WorkDayMapper.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

fun WorkDayEntity.toDomain(): WorkDay{
    return WorkDay(
        id = this.id,
        companyId = this.companyId,
        date = Date(this.date),
        isHalfTime = this.isHalfTime,
        amountPaid = this.amountPaid,
        isPaid = this.isPaid,
        description = this.description
    )
}

fun WorkDay.toEntity(): WorkDayEntity{
    return WorkDayEntity(
        id = this.id,
        companyId = this.companyId,
        date = this.date.time,
        isHalfTime = this.isHalfTime,
        amountPaid = this.amountPaid,
        isPaid = this.isPaid,
        description = this.description
    )
}
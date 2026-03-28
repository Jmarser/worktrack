package com.jmarser.worktrack.data.mapper

import com.jmarser.worktrack.data.local.database.entity.ExpanseEntity
import com.jmarser.worktrack.domain.model.Expanse

/**
 * Project: WorkTrack
 * File: ExpanseMapper.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

fun ExpanseEntity.toDomain(): Expanse{
    return Expanse(
        id = this.id,
        workDayId = this.workDayId,
        description = this.description,
        amount = this.amount,
        isPaid = this.isPaid
    )
}

fun Expanse.toEntity(): ExpanseEntity{
    return ExpanseEntity(
        id = this.id,
        workDayId = this.workDayId,
        description = this.description,
        amount = this.amount,
        isPaid = this.isPaid
    )
}
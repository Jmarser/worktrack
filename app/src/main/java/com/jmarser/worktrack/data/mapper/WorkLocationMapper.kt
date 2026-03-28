package com.jmarser.worktrack.data.mapper

import com.jmarser.worktrack.data.local.database.entity.WorkLocationEntity
import com.jmarser.worktrack.domain.model.WorkLocation

/**
 * Project: WorkTrack
 * File: WorkLocationMapper.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

fun WorkLocationEntity.toDomain(): WorkLocation{
    return WorkLocation(
        id = this.id,
        workDayId = this.workDayId,
        name = this.name,
        description = this.description
    )
}

fun WorkLocation.toEntity(): WorkLocationEntity{
    return WorkLocationEntity(
        id = this.id,
        workDayId = this.workDayId,
        name = this.name,
        description = this.description
    )
}
package com.jmarser.worktrack.data.local.database.model

import androidx.room.Embedded
import androidx.room.Relation
import com.jmarser.worktrack.data.local.database.entity.CompanyEntity
import com.jmarser.worktrack.data.local.database.entity.WorkDayEntity

data class CompanyWithWorkDays(
    @Embedded val company : CompanyEntity,

    @Relation(
        entity = WorkDayEntity::class,
        parentColumn = "id",
        entityColumn = "companyId"
    )
    val workDays: List<WorkDayWithDetails>
)

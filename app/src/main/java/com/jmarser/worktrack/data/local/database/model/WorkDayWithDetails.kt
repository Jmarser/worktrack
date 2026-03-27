package com.jmarser.worktrack.data.local.database.model

import androidx.room.Embedded
import androidx.room.Relation
import com.jmarser.worktrack.data.local.database.entity.ExpanseEntity
import com.jmarser.worktrack.data.local.database.entity.WorkDayEntity
import com.jmarser.worktrack.data.local.database.entity.WorkLocationEntity

/**
 * Este objeto representa un "Día de trabajo completo".
 * No es una tabla en sí, sino una vista combinada de varias.
 *
 * Con este objeto le indicamos a room las relaciones entre las tablas
 * y los datos que debe obtener de ellas
 */
data class WorkDayWithDetails(
    // Entidad principal
    @Embedded val workDay: WorkDayEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "workDayId"
    )
    val locations: List<WorkLocationEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "workDayId"
    )
    val expanses: List<ExpanseEntity>,

    )

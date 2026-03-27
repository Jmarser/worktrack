package com.jmarser.worktrack.data.local.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "work_location",
    foreignKeys = [
        ForeignKey(
            entity = WorkDayEntity::class,
            parentColumns = ["id"],
            childColumns = ["workDayId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class WorkLocationEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val workDayId: Long,
    val name: String,
    val description: String? = null
)

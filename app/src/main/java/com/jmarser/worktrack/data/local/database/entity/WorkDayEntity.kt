package com.jmarser.worktrack.data.local.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "work_day",
    foreignKeys = [
        ForeignKey(
            entity = CompanyEntity::class,
            parentColumns = ["id"],
            childColumns = ["companyId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("companyId")]
)
data class WorkDayEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val companyId: Long,
    val date: Long,
    val isHalfTime: Boolean,
    val amountPaid: Double,
    val isPaid: Boolean = false,
    val description: String? = null,
)

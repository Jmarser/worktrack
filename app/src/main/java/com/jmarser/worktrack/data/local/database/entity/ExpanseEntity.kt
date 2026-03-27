package com.jmarser.worktrack.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expanse")
data class ExpanseEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val workDayId: Long,
    val description: String,
    val amount: Double,
    val isPaid: Boolean = false,

)

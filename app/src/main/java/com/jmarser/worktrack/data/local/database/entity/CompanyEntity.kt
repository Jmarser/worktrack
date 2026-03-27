package com.jmarser.worktrack.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company")
data class CompanyEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val fullTimePrice: Double? = null,
    val HalfTimePrice: Double? = null,

)

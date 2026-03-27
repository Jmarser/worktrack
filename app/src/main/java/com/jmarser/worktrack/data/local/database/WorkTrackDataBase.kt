package com.jmarser.worktrack.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jmarser.worktrack.data.local.database.dao.CompanyDao
import com.jmarser.worktrack.data.local.database.dao.ExpanseDao
import com.jmarser.worktrack.data.local.database.dao.WorkDayDao
import com.jmarser.worktrack.data.local.database.dao.WorkLocationDao
import com.jmarser.worktrack.data.local.database.entity.CompanyEntity
import com.jmarser.worktrack.data.local.database.entity.ExpanseEntity
import com.jmarser.worktrack.data.local.database.entity.WorkDayEntity
import com.jmarser.worktrack.data.local.database.entity.WorkLocationEntity
import com.jmarser.worktrack.data.local.database.util.Converters

/**
 * Project: WorkTrack
 * File: WorkTrackDataBase.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

@Database(
    entities = [
        CompanyEntity::class,
        WorkDayEntity::class,
        WorkLocationEntity::class,
        ExpanseEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class WorkTrackDataBase: RoomDatabase() {

    abstract fun companyDao(): CompanyDao
    abstract fun workDayDao(): WorkDayDao
    abstract fun workLocationDao(): WorkLocationDao
    abstract fun expanseDao(): ExpanseDao

    companion object{
        const val DATABASE_NAME = "worktrack_db"
    }

}
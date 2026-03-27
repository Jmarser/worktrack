package com.jmarser.worktrack.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jmarser.worktrack.data.local.database.entity.WorkLocationEntity
import kotlinx.coroutines.flow.Flow

/**
 * Project: WorkTrack
 * File: WorkLocationDao.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

@Dao
interface WorkLocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: WorkLocationEntity)

    @Update
    suspend fun updateLocation(location: WorkLocationEntity)

    @Delete
    suspend fun deleteLocation(location: WorkLocationEntity)

    @Query("SELECT * FROM work_location WHERE workDayId = :workDayId")
    fun getLocationsByday(workDayId: Long): Flow<List<WorkLocationEntity>>

}
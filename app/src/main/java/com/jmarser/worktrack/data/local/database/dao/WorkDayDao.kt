package com.jmarser.worktrack.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.jmarser.worktrack.data.local.database.entity.ExpanseEntity
import com.jmarser.worktrack.data.local.database.entity.WorkDayEntity
import com.jmarser.worktrack.data.local.database.model.WorkDayWithDetails
import kotlinx.coroutines.flow.Flow

/**
 * Project: WorkTrack
 * File: WorkDayDao.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

@Dao
interface WorkDayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkDay(workDay: WorkDayEntity): Long

    @Update
    suspend fun updateWorkDay(workDay: WorkDayEntity)

    @Query("UPDATE work_day SET isPaid = :paid WHERE id = :workDayId")
    suspend fun updatePaymentStatus(workDayId: Long, paid: Boolean)

    @Transaction
    @Query("SELECT * FROM work_day WHERE companyId = :companyId ORDER BY date DESC")
    fun getWorkDaysByCompany(companyId: Long): Flow<List<WorkDayWithDetails>>

    @Transaction
    @Query("SELECT* FROM work_day WHERE id = :workDayId")
    suspend fun getWorkDayDetails(workDayId: Long): WorkDayWithDetails?

    @Query("DELETE FROM work_day WHERE id = :id")
    suspend fun deleteWorkDay(id: Long)
}
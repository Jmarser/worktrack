package com.jmarser.worktrack.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jmarser.worktrack.data.local.database.entity.ExpanseEntity
import kotlinx.coroutines.flow.Flow

/**
 * Project: WorkTrack
 * File: ExpanseDao.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

@Dao
interface ExpanseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpanse(expanse: ExpanseEntity)

    @Update
    suspend fun updateExpanse(expanse: ExpanseEntity)

    @Delete
    suspend fun deleteExpanse(expanse: ExpanseEntity)

    @Query("UPDATE expanse SET isPaid = :paid WHERE id = :expanseId")
    suspend fun updateExpansePaymentStatus(expanseId: Long, paid: Boolean)

    @Query("SELECT * FROM expanse WHERE workDayId = :workDayId")
    fun getExpansesByDay(workDayId: Long): Flow<List<ExpanseEntity>>
}
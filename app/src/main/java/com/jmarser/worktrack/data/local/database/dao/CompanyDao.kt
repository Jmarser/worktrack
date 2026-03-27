package com.jmarser.worktrack.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jmarser.worktrack.data.local.database.entity.CompanyEntity
import kotlinx.coroutines.flow.Flow

/**
 * Project: WorkTrack
 * File: CompanyDao.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

@Dao
interface CompanyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompany(company: CompanyEntity)

    @Update
    suspend fun updateCompany(company: CompanyEntity)

    @Delete
    suspend fun deleteCompany(company: CompanyEntity)

    @Query("SELECT * FROM company WHERE id = :id")
    suspend fun getCompanyById(id: Long): CompanyEntity?

    @Query("SELECT * FROM company ORDER BY name ASC")
    fun getAllCompanies(): Flow<List<CompanyEntity>>
}
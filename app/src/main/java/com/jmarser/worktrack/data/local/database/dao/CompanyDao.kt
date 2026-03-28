package com.jmarser.worktrack.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.jmarser.worktrack.data.local.database.entity.CompanyEntity
import com.jmarser.worktrack.data.local.database.model.CompanySummaryEntity
import com.jmarser.worktrack.data.local.database.model.CompanyWithWorkDays
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
    suspend fun insertCompany(company: CompanyEntity): Long

    @Update
    suspend fun updateCompany(company: CompanyEntity)

    @Delete
    suspend fun deleteCompany(company: CompanyEntity)

    @Query("SELECT * FROM company WHERE id = :id")
    fun getCompanyById(id: Long): Flow<CompanyEntity?>

    @Query("SELECT * FROM company ORDER BY name ASC")
    fun getAllCompanies(): Flow<List<CompanyEntity>>

    @Transaction
    @Query("SELECT * FROM company WHERE id = :companyId")
    fun getComapnyWithWorksDays(companyId: Long): Flow<CompanyWithWorkDays?>

    @Query("""SELECT 
        c.id as id, 
        c.name as name, 
        COUNT(w.id) as totalDaysWorked,
        SUM(CASE WHEN w.isPaid = 0 THEN 1 ELSE 0 END) as daysPending
    FROM company c
    LEFT JOIN work_day w ON c.id = w.companyId
    GROUP BY c.id
    ORDER BY c.name ASC
""")
    fun getCompaniesSummary(): Flow<List<CompanySummaryEntity>>
}
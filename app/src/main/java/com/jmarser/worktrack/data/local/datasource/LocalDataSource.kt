package com.jmarser.worktrack.data.local.datasource

import com.jmarser.worktrack.data.local.database.entity.CompanyEntity
import com.jmarser.worktrack.data.local.database.entity.ExpanseEntity
import com.jmarser.worktrack.data.local.database.entity.WorkDayEntity
import com.jmarser.worktrack.data.local.database.entity.WorkLocationEntity
import com.jmarser.worktrack.data.local.database.model.CompanySummaryEntity
import com.jmarser.worktrack.data.local.database.model.CompanyWithWorkDays
import com.jmarser.worktrack.data.local.database.model.WorkDayWithDetails
import kotlinx.coroutines.flow.Flow

/**
 * Project: WorkTrack
 * File: LocalDataSource.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

interface LocalDataSource {

    // COMPANY
    suspend fun insertCompany(company: CompanyEntity): Long
    suspend fun updateCompany(company: CompanyEntity)
    suspend fun deleteCompany(company: CompanyEntity)
    fun getCompanyById(companyId: Long): Flow<CompanyEntity?>
    fun getAllCompanies(): Flow<List<CompanyEntity>>
    fun getCompanyWithWorkDays(companyId: Long): Flow<CompanyWithWorkDays?>
    fun getCompaniesSummary(): Flow<List<CompanySummaryEntity>>

    // WORKDAY
    suspend fun insertWorkDay(workDay: WorkDayEntity): Long
    suspend fun updateWorkDay(workDay: WorkDayEntity)
    suspend fun updateWorkDayPaymentStatus(workDayId: Long, isPaid: Boolean)
    suspend fun deleteWorkDay(workDayId: Long)
    fun getWorkdayDetails(workDayId: Long): Flow<WorkDayWithDetails?>
    fun getWorkDaysByCompany(companyId: Long): Flow<List<WorkDayWithDetails>>

    // WORKLOCATION
    suspend fun insertLocation(location: WorkLocationEntity)
    suspend fun updateLocation(location: WorkLocationEntity)
    suspend fun deleleLocation(location: WorkLocationEntity)
    fun getLocationsByDay(workDayId: Long): Flow<List<WorkLocationEntity?>>

    // EXPANSE
    suspend fun insertExpanse(expanse: ExpanseEntity)
    suspend fun updateExpanse(expanse: ExpanseEntity)
    suspend fun deleteExpanse(expanse: ExpanseEntity)
    suspend fun updateExpansePaymentStatus(expanseId: Long, isPaid: Boolean)
    fun getExpansesByDay(workDayId: Long): Flow<List<ExpanseEntity?>>
}
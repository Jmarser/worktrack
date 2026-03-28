package com.jmarser.worktrack.data.local.datasource

import com.jmarser.worktrack.data.local.database.dao.CompanyDao
import com.jmarser.worktrack.data.local.database.dao.ExpanseDao
import com.jmarser.worktrack.data.local.database.dao.WorkDayDao
import com.jmarser.worktrack.data.local.database.dao.WorkLocationDao
import com.jmarser.worktrack.data.local.database.entity.CompanyEntity
import com.jmarser.worktrack.data.local.database.entity.ExpanseEntity
import com.jmarser.worktrack.data.local.database.entity.WorkDayEntity
import com.jmarser.worktrack.data.local.database.entity.WorkLocationEntity
import com.jmarser.worktrack.data.local.database.model.CompanySummaryEntity
import com.jmarser.worktrack.data.local.database.model.CompanyWithWorkDays
import com.jmarser.worktrack.data.local.database.model.WorkDayWithDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Project: WorkTrack
 * File: LocalDataSourceImpl.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

class LocalDataSourceImpl @Inject constructor(
    private val companyDao: CompanyDao,
    private val workDayDao: WorkDayDao,
    private val workLocationDao: WorkLocationDao,
    private val expanseDao: ExpanseDao
) : LocalDataSource {

    // COMPANY
    override suspend fun insertCompany(company: CompanyEntity): Long {
        return companyDao.insertCompany(company)
    }

    override suspend fun updateCompany(company: CompanyEntity) {
        return companyDao.updateCompany(company)
    }

    override suspend fun deleteCompany(company: CompanyEntity) {
        return companyDao.deleteCompany(company)
    }

    override fun getCompanyById(companyId: Long): Flow<CompanyEntity?> {
        return companyDao.getCompanyById(companyId)
    }

    override fun getAllCompanies(): Flow<List<CompanyEntity>> {
        return companyDao.getAllCompanies()
    }

    override fun getCompanyWithWorkDays(companyId: Long): Flow<CompanyWithWorkDays?> {
        return companyDao.getComapnyWithWorksDays(companyId)
    }

    override fun getCompaniesSummary(): Flow<List<CompanySummaryEntity>> {
        return companyDao.getCompaniesSummary()
    }

    // WORKDAY
    override suspend fun insertWorkDay(workDay: WorkDayEntity): Long {
        return workDayDao.insertWorkDay(workDay)
    }

    override suspend fun updateWorkDay(workDay: WorkDayEntity) {
        return workDayDao.updateWorkDay(workDay)
    }

    override suspend fun updateWorkDayPaymentStatus(
        workDayId: Long,
        isPaid: Boolean
    ) {
        return workDayDao.updatePaymentStatus(workDayId, isPaid)
    }

    override suspend fun deleteWorkDay(workDayId: Long) {
        return workDayDao.deleteWorkDay(workDayId)
    }

    override fun getWorkdayDetails(workDayId: Long): Flow<WorkDayWithDetails?> {
        return workDayDao.getWorkDayDetails(workDayId)
    }

    override fun getWorkDaysByCompany(companyId: Long): Flow<List<WorkDayWithDetails>> {
        return workDayDao.getWorkDaysByCompany(companyId)
    }

    // WORKLOCATION
    override suspend fun insertLocation(location: WorkLocationEntity) {
        return workLocationDao.insertLocation(location)
    }

    override suspend fun updateLocation(location: WorkLocationEntity) {
        return workLocationDao.updateLocation(location)
    }

    override suspend fun deleleLocation(location: WorkLocationEntity) {
        return workLocationDao.deleteLocation(location)
    }

    override fun getLocationsByDay(workDayId: Long): Flow<List<WorkLocationEntity?>> {
        return workLocationDao.getLocationsByday(workDayId)
    }

    //EXPANSE
    override suspend fun insertExpanse(expanse: ExpanseEntity) {
        return expanseDao.insertExpanse(expanse)
    }

    override suspend fun updateExpanse(expanse: ExpanseEntity) {
        return expanseDao.updateExpanse(expanse)
    }

    override suspend fun deleteExpanse(expanse: ExpanseEntity) {
        return expanseDao.deleteExpanse(expanse)
    }

    override suspend fun updateExpansePaymentStatus(
        expanseId: Long,
        isPaid: Boolean
    ) {
        return expanseDao.updateExpansePaymentStatus(expanseId, isPaid)
    }

    override fun getExpansesByDay(workDayId: Long): Flow<List<ExpanseEntity?>> {
        return expanseDao.getExpansesByDay(workDayId)
    }
}
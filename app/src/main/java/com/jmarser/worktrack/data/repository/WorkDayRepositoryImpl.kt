package com.jmarser.worktrack.data.repository

import com.jmarser.worktrack.core.domain.coroutines.DispatchersProvider
import com.jmarser.worktrack.data.local.datasource.LocalDataSource
import com.jmarser.worktrack.data.mapper.toDomain
import com.jmarser.worktrack.data.mapper.toEntity
import com.jmarser.worktrack.domain.model.WorkDay
import com.jmarser.worktrack.domain.model.WorkDayComplete
import com.jmarser.worktrack.domain.repository.WorkDayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Project: WorkTrack
 * File: WorkDayRepositoryImpl.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/03/2026
 */

class WorkDayRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val dispatchers: DispatchersProvider
): WorkDayRepository {
    override suspend fun insertWorkDay(workDay: WorkDay): Long {
        return withContext(dispatchers.io){
            localDataSource.insertWorkDay(workDay.toEntity())
        }
    }

    override suspend fun updateWorkDay(workDay: WorkDay) {
        return withContext(dispatchers.io){
            localDataSource.updateWorkDay(workDay.toEntity())
        }
    }

    override suspend fun updatePaymentStatus(workDayId: Long, isPaid: Boolean) {
        return withContext(dispatchers.io){
            localDataSource.updateWorkDayPaymentStatus(workDayId, isPaid)
        }
    }

    override suspend fun deleteWorkDay(workDayId: Long) {
        return withContext(dispatchers.io){
            localDataSource.deleteWorkDay(workDayId)
        }
    }

    override fun getWorkDayDetails(workDayId: Long): Flow<WorkDayComplete?> {
        return localDataSource.getWorkdayDetails(workDayId)
            .map { it?.toDomain() }
            .flowOn(dispatchers.io)
    }

    override fun getWorkDaysByCompany(companyId: Long): Flow<List<WorkDayComplete>> {
        return localDataSource.getWorkDaysByCompany(companyId)
            .map { list -> list.map { it.toDomain() } }
            .flowOn(dispatchers.io)
    }
}
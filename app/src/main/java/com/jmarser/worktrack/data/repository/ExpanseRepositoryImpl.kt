package com.jmarser.worktrack.data.repository

import com.jmarser.worktrack.core.domain.coroutines.DispatchersProvider
import com.jmarser.worktrack.data.local.datasource.LocalDataSource
import com.jmarser.worktrack.data.mapper.toDomain
import com.jmarser.worktrack.data.mapper.toEntity
import com.jmarser.worktrack.domain.model.Expanse
import com.jmarser.worktrack.domain.repository.ExpanseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Project: WorkTrack
 * File: ExpanseRepositoryImpl.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/03/2026
 */

class ExpanseRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val dispatchers: DispatchersProvider
) : ExpanseRepository {
    override suspend fun insertExpanse(expanse: Expanse) {
        return withContext(dispatchers.io) {
            localDataSource.insertExpanse(expanse.toEntity())
        }
    }

    override suspend fun updateExpanse(expanse: Expanse) {
        return localDataSource.updateExpanse(expanse.toEntity())
    }

    override suspend fun deleteExpanse(expanse: Expanse) {
        return localDataSource.deleteExpanse(expanse.toEntity())
    }

    override suspend fun updateExpansePaymentStatus(
        expanseId: Long,
        isPaid: Boolean
    ) {
        return withContext(dispatchers.io){
            localDataSource.updateExpansePaymentStatus(expanseId, isPaid)
        }
    }

    override fun getExpansesByDay(workDayId: Long): Flow<List<Expanse?>> {
        return localDataSource.getExpansesByDay(workDayId)
            .map { list -> list.map { it?.toDomain() } }
            .flowOn(dispatchers.io)
    }

}
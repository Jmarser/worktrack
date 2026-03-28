package com.jmarser.worktrack.data.repository

import com.jmarser.worktrack.core.domain.coroutines.DispatchersProvider
import com.jmarser.worktrack.data.local.datasource.LocalDataSource
import com.jmarser.worktrack.data.mapper.toDomain
import com.jmarser.worktrack.data.mapper.toEntity
import com.jmarser.worktrack.domain.model.WorkLocation
import com.jmarser.worktrack.domain.repository.WorkLocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Project: WorkTrack
 * File: WorkLocationRepositoryImpl.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/03/2026
 */

class WorkLocationRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val dispatchers: DispatchersProvider
): WorkLocationRepository {
    override suspend fun insertLocation(location: WorkLocation) {
        return withContext(dispatchers.io) {
            localDataSource.insertLocation(location.toEntity())
        }
    }

    override suspend fun updateLocation(location: WorkLocation) {
        return withContext(dispatchers.io) {
            localDataSource.updateLocation(location.toEntity())
        }
    }

    override suspend fun deleteLocation(location: WorkLocation) {
        return withContext(dispatchers.io) {
            localDataSource.deleleLocation(location.toEntity())
        }
    }

    override fun getLocationsByDay(workId: Long): Flow<List<WorkLocation?>> {
        return localDataSource.getLocationsByDay(workId)
            .map { list -> list.map { it?.toDomain() } }
            .flowOn(dispatchers.io)
    }

}
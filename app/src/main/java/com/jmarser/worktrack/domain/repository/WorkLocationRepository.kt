package com.jmarser.worktrack.domain.repository

import com.jmarser.worktrack.domain.model.WorkLocation
import kotlinx.coroutines.flow.Flow

/**
 * Project: WorkTrack
 * File: WorkLocationRepository.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/03/2026
 */

interface WorkLocationRepository {
    suspend fun insertLocation(location: WorkLocation)
    suspend fun updateLocation(location: WorkLocation)
    suspend fun deleteLocation(location: WorkLocation)
    fun getLocationsByDay(workId: Long): Flow<List<WorkLocation?>>
}
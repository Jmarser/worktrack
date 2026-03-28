package com.jmarser.worktrack.domain.repository

import com.jmarser.worktrack.domain.model.Expanse
import kotlinx.coroutines.flow.Flow

/**
 * Project: WorkTrack
 * File: ExpanseRepository.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/03/2026
 */

interface ExpanseRepository {
    suspend fun insertExpanse(expanse: Expanse)
    suspend fun updateExpanse(expanse: Expanse)
    suspend fun deleteExpanse(expanse: Expanse)
    suspend fun updateExpansePaymentStatus(expanseId: Long, isPaid: Boolean)
    fun getExpansesByDay(workDayId: Long): Flow<List<Expanse?>>
}
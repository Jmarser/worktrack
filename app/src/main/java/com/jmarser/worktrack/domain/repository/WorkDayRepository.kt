package com.jmarser.worktrack.domain.repository

import com.jmarser.worktrack.domain.model.WorkDay
import com.jmarser.worktrack.domain.model.WorkDayComplete
import kotlinx.coroutines.flow.Flow

/**
 * Project: WorkTrack
 * File: WorkDayRepository.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/03/2026
 */

interface WorkDayRepository {
    suspend fun insertWorkDay(workDay: WorkDay): Long
    suspend fun updateWorkDay(workDay: WorkDay)
    suspend fun updatePaymentStatus(workDayId: Long, isPaid: Boolean)
    suspend fun deleteWorkDay(workDayId: Long)
    fun getWorkDayDetails(workDayId: Long): Flow<WorkDayComplete?>
    fun getWorkDaysByCompany(companyId: Long): Flow<List<WorkDayComplete>>
}
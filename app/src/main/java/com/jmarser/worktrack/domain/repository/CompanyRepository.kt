package com.jmarser.worktrack.domain.repository

import com.jmarser.worktrack.domain.model.Company
import com.jmarser.worktrack.domain.model.CompanyComplete
import com.jmarser.worktrack.domain.model.CompanySummary
import kotlinx.coroutines.flow.Flow

/**
 * Project: WorkTrack
 * File: CompanyRepository.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

interface CompanyRepository {

    suspend fun addCompany(company: Company): Long
    suspend fun updateCompany(company: Company)
    suspend fun deleteCompany(company: Company)
    suspend fun existsCompanyByName(name: String): Boolean
    suspend fun deleteCompanyById(comapnyId: Long)
    fun getCompanyById(id: Long): Flow<Company?>
    fun getAllCompanies(): Flow<List<Company>>
    fun getCompanyComplete(id: Long): Flow<CompanyComplete?>
    fun getCompaniesSummary(): Flow<List<CompanySummary>>
}
package com.jmarser.worktrack.data.repository

import com.jmarser.worktrack.core.data.repository.BaseRepository
import com.jmarser.worktrack.core.domain.coroutines.DispatchersProvider
import com.jmarser.worktrack.data.local.datasource.LocalDataSource
import com.jmarser.worktrack.data.mapper.toDomain
import com.jmarser.worktrack.data.mapper.toEntity
import com.jmarser.worktrack.domain.model.Company
import com.jmarser.worktrack.domain.model.CompanyComplete
import com.jmarser.worktrack.domain.model.CompanySummary
import com.jmarser.worktrack.domain.repository.CompanyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Project: WorkTrack
 * File: CompanyRepositoryImpl.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

class CompanyRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    dispatchers: DispatchersProvider
) : BaseRepository(dispatchers), CompanyRepository {
    override suspend fun addCompany(company: Company): Long {
        return doWork {
            localDataSource.insertCompany(company.toEntity())
        }
    }

    override suspend fun updateCompany(company: Company) {
        return doWork {
            localDataSource.updateCompany(company.toEntity())
        }
    }

    override suspend fun deleteComapny(company: Company) {
        return doWork {
            localDataSource.deleteCompany(company.toEntity())
        }
    }

    override fun getCompanyById(id: Long): Flow<Company?> {
        return localDataSource.getCompanyById(id)
            .map { entity -> entity?.toDomain() }.asIoFlow()
    }

    override fun getAllCompanies(): Flow<List<Company>> {
        return localDataSource.getAllCompanies()
            .map { entities -> entities.map { it.toDomain() } }.asIoFlow()
    }

    override fun getCompanyComplete(id: Long): Flow<CompanyComplete?> {
        return localDataSource.getCompanyWithWorkDays(id)
            .map { it?.toDomain() }
            .asIoFlow()
    }

    override fun getCompaniesSummary(): Flow<List<CompanySummary>> {
        return localDataSource.getCompaniesSummary()
            .map { entities -> entities.map { it.toDomain() } }
            .asIoFlow()
    }
}
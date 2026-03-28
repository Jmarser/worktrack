package com.jmarser.worktrack.di

import com.jmarser.worktrack.data.repository.CompanyRepositoryImpl
import com.jmarser.worktrack.data.repository.ExpanseRepositoryImpl
import com.jmarser.worktrack.data.repository.WorkDayRepositoryImpl
import com.jmarser.worktrack.data.repository.WorkLocationRepositoryImpl
import com.jmarser.worktrack.domain.repository.CompanyRepository
import com.jmarser.worktrack.domain.repository.ExpanseRepository
import com.jmarser.worktrack.domain.repository.WorkDayRepository
import com.jmarser.worktrack.domain.repository.WorkLocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Project: WorkTrack
 * File: RepositoryModule.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/03/2026
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyRepository(companyRepositoryImpl: CompanyRepositoryImpl): CompanyRepository

    @Binds
    @Singleton
    abstract fun bindWorkDayRepository(workDayRepositoryImpl: WorkDayRepositoryImpl): WorkDayRepository

    @Binds
    @Singleton
    abstract fun bindWorkLocationRepository(workLocationRepositoryImpl: WorkLocationRepositoryImpl): WorkLocationRepository

    @Binds
    @Singleton
    abstract fun bindExpanseRepository(expanseRepositoryImpl: ExpanseRepositoryImpl): ExpanseRepository
}
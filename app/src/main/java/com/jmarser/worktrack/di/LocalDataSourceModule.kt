package com.jmarser.worktrack.di

import com.jmarser.worktrack.data.local.datasource.LocalDataSource
import com.jmarser.worktrack.data.local.datasource.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Project: WorkTrack
 * File: LocalDataSourceModule.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(
        localDataSourceImpl: LocalDataSourceImpl
    ): LocalDataSource
}
package com.jmarser.worktrack.di

import com.jmarser.worktrack.core.data.coroutines.DispatchersProviderImpl
import com.jmarser.worktrack.core.domain.coroutines.DispatchersProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Project: WorkTrack
 * File: DispatchersModule.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatchersModule {

    @Binds
    @Singleton
    abstract fun bindDispatchersProvider(dispatchersProviderImpl: DispatchersProviderImpl): DispatchersProvider
}
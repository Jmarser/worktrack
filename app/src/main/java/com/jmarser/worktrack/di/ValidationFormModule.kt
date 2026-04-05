package com.jmarser.worktrack.di

import com.jmarser.worktrack.core.domain.validation.ValidationForm
import com.jmarser.worktrack.core.domain.validation.ValidationFormImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ValidationFormModule {

    @Provides
    @Singleton
    fun provideValidationForm(): ValidationForm = ValidationFormImpl()
}
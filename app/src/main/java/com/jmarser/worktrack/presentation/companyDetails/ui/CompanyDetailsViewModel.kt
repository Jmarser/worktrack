package com.jmarser.worktrack.presentation.companyDetails.ui

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

/**
 * Project: WorkTrack
 * File: CompanyDetailsViewModel.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 11/04/2026
 */

@HiltViewModel(assistedFactory = CompanyDetailsViewModel.Factory::class)
class CompanyDetailsViewModel @AssistedInject constructor(
    @Assisted private val companyId: Long
): ViewModel(){

    @AssistedFactory
    interface Factory{
        fun create(companyId: Long): CompanyDetailsViewModel
    }

    init {
        if (companyId != -1L){
            getDetailsCompany()
        }
    }

    private fun getDetailsCompany(){

    }
}
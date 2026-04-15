package com.jmarser.worktrack.presentation.companyDetails.ui

import androidx.core.os.registerForAllProfilingResults
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarser.worktrack.core.error.ValidationError
import com.jmarser.worktrack.domain.useCase.GetCompanyCompleteByIdUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

/**
 * Project: WorkTrack
 * File: CompanyDetailsViewModel.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 11/04/2026
 */

@HiltViewModel(assistedFactory = CompanyDetailsViewModel.Factory::class)
class CompanyDetailsViewModel @AssistedInject constructor(
    @Assisted private val companyId: Long,
    private val getDetailsCompanyUseCase: GetCompanyCompleteByIdUseCase
): ViewModel(){

    @AssistedFactory
    interface Factory{
        fun create(companyId: Long): CompanyDetailsViewModel
    }

    private val _uiState = MutableStateFlow<CompanyDetailsState>(CompanyDetailsState.Idle)
    val uiState: StateFlow<CompanyDetailsState> = _uiState.asStateFlow()

    init {
        if (companyId != -1L){
            getDetailsCompany()
        }else{
            _uiState.value = CompanyDetailsState.Failure(ValidationError.CompanyNotFound)
        }
    }

    private fun getDetailsCompany(){
        getDetailsCompanyUseCase(companyId)
            .onStart {
                _uiState.value = CompanyDetailsState.Loading
            }
            .onEach { result ->
                _uiState.value = result?.let {
                    CompanyDetailsState.Success(
                        data = it,
                        totalDays = it.totalDaysWorked,
                        totalDaysPending = it.daysPendingToPay,
                        totalPaid = it.totalAmountPaid,
                        totalPending = it.totalAmountPending
                    )
                } ?: CompanyDetailsState.Empty
            }
            .catch {
                _uiState.value = CompanyDetailsState.Failure(ValidationError.ErrorUnknown)
            }
            .launchIn(viewModelScope)
    }
}
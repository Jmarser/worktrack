package com.jmarser.worktrack.presentation.companyList.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarser.worktrack.R
import com.jmarser.worktrack.core.error.ValidationError
import com.jmarser.worktrack.domain.model.CompanySummary
import com.jmarser.worktrack.domain.useCase.DeleteCompanyByIdUseCase
import com.jmarser.worktrack.domain.useCase.GetHomeDataUseCase
import com.jmarser.worktrack.presentation.companyList.ui.CompanyListEffect.NavigateToCreateCompany
import com.jmarser.worktrack.presentation.companyList.ui.CompanyListEffect.NavigateToDetailsCompany
import com.jmarser.worktrack.presentation.companyList.ui.CompanyListEffect.NavigateToEditCompany
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Project: WorkTrack
 * File: CompanyListViewModel.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 31/03/2026
 */

@HiltViewModel
class CompanyListViewModel @Inject constructor(
    private val getHomeDataUseCase: GetHomeDataUseCase,
    private val deleteCompanyByIdUseCase: DeleteCompanyByIdUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow<CompanyListState>(CompanyListState.Idle)
    val uiState: StateFlow<CompanyListState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<CompanyListEffect>()
    val uiEffect: SharedFlow<CompanyListEffect> = _uiEffect.asSharedFlow()

    private val _deleteDialogState = MutableStateFlow<CompanySummary?>(null)
    val deleteDialogState: StateFlow<CompanySummary?> = _deleteDialogState.asStateFlow()

    fun onEvent(event: CompanyListEvent) {
        when (event) {
            CompanyListEvent.onClickCreateCompany -> emitEffect(NavigateToCreateCompany)
            is CompanyListEvent.onClickDeleteCompany -> {
                deleteCompany(event.companyId)
            }

            is CompanyListEvent.onClickEditCompany -> emitEffect(NavigateToEditCompany(event.companyId))
            is CompanyListEvent.onClickNavigateToDetails -> emitEffect(NavigateToDetailsCompany(0))
            is CompanyListEvent.ToggleDeleteDialogState -> {
                _deleteDialogState.value = event.companySummary
            }

            CompanyListEvent.onRetry -> getCompanies()
        }
    }

    init {
        getCompanies()
    }

    private fun getCompanies() {
        getHomeDataUseCase()
            .onStart { _uiState.value = CompanyListState.Loading }
            .onEach { result ->
                if (result.companies.isEmpty()) {
                    _uiState.value = CompanyListState.Empty
                } else {
                    _uiState.value = CompanyListState.Success(data = result)
                }
            }
            .catch {
                _uiState.value = CompanyListState.Failure(ValidationError.ErrorUnknown)
            }
            .launchIn(viewModelScope)
    }

    private fun deleteCompany(companyId: Long) {

        viewModelScope.launch {
            val result = deleteCompanyByIdUseCase(companyId)
            _deleteDialogState.value = null
            result.onSuccess {
                emitEffect(CompanyListEffect.ShowMessage(R.string.delete_company_successfully))

            }.onFailure {
                emitEffect(CompanyListEffect.ShowMessage(R.string.error_delete_company))
            }
        }
    }

    private fun emitEffect(effect: CompanyListEffect) {
        viewModelScope.launch {
            _uiEffect.emit(effect)
        }
    }
}
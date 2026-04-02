package com.jmarser.worktrack.presentation.companyList.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarser.worktrack.domain.useCase.GetHomeDataUseCase
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
    private val getHomeDataUseCase: GetHomeDataUseCase
): ViewModel() {


    private val _uiState = MutableStateFlow<CompanyListState>(CompanyListState.Idle)
    val uiState: StateFlow<CompanyListState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<CompanyListEffect>()
    val uiEffect: SharedFlow<CompanyListEffect> = _uiEffect.asSharedFlow()

    fun onEvent(event: CompanyListEvent){
        when(event){
            CompanyListEvent.onClickCreateCompany -> emitEffect(CompanyListEffect.NavigateToCreateCompany)
            is CompanyListEvent.onClickDeleteCompany -> emitEffect(CompanyListEffect.NavigateToDeleteCompany(0))
            is CompanyListEvent.onClickEditCompany -> emitEffect(CompanyListEffect.NavigateToEditCompany(0))
            is CompanyListEvent.onClickNavigateToDetails -> emitEffect(CompanyListEffect.NavigateToDetailsCompany(0))
            is CompanyListEvent.ShowSnackbar -> emitEffect(CompanyListEffect.ShowMessage(event.message))
        }
    }

    init {
        getCompanies()
    }

    private fun getCompanies(){
        getHomeDataUseCase()
            .onStart { _uiState.value = CompanyListState.Loading }
            .onEach {result ->
                if (result.companies.isEmpty()){
                    _uiState.value = CompanyListState.Empty
                }else{
                    _uiState.value = CompanyListState.Success(data = result)
                }
            }
            .catch {
                _uiState.value = CompanyListState.Failure("No hay empresas disponibles")
            }
            .launchIn(viewModelScope)
    }

    private fun emitEffect(effect: CompanyListEffect){
        viewModelScope.launch {
            _uiEffect.emit(effect)
        }
    }
}
package com.jmarser.worktrack.presentation.createCompany.ui

import android.util.Log
import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarser.worktrack.R
import com.jmarser.worktrack.core.error.ValidationError
import com.jmarser.worktrack.domain.model.Company
import com.jmarser.worktrack.domain.model.CurrencyType
import com.jmarser.worktrack.domain.useCase.RegisterCompanyUseCase
import com.jmarser.worktrack.domain.useCase.ValidateCompanyNameUseCase
import com.jmarser.worktrack.domain.useCase.ValidationFormUseCase
import com.jmarser.worktrack.domain.useCase.GetCompanyByIdUseCase
import com.jmarser.worktrack.domain.useCase.UpdateCompanyUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Project: WorkTrack
 * File: CreateCompanyViewModel.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/04/2026
 */

@HiltViewModel(assistedFactory = CreateCompanyViewModel.Factory::class)
class CreateCompanyViewModel @AssistedInject constructor(
    @Assisted private val companyId: Long,
    private val validateForm: ValidationFormUseCase,
    private val validateCompanyExists: ValidateCompanyNameUseCase,
    private val registerCompanyUseCase: RegisterCompanyUseCase,
    private val getCompanyByIdUseCase: GetCompanyByIdUseCase,
    private val updateCompanyUseCase: UpdateCompanyUseCase
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(companyId: Long): CreateCompanyViewModel
    }

    init {
        if (companyId != -1L) {
            loadCompanyData()
        }
    }

    private val _formState = MutableStateFlow<CreateCompanyFormState>(CreateCompanyFormState())
    val formState: StateFlow<CreateCompanyFormState> = _formState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<CreateCompanyEffect>()
    val uiEffect: SharedFlow<CreateCompanyEffect> = _uiEffect.asSharedFlow()

    fun onEvent(event: CreateCompanyEvent) {
        when (event) {
            is CreateCompanyEvent.SetCompanyName -> setCompanyName(event.name)
            is CreateCompanyEvent.SetCurrencyType -> setCurrencyType(event.currency)
            is CreateCompanyEvent.SetFullTimePrice -> setFullTimePrice(event.price)
            is CreateCompanyEvent.SetHalfTimePrice -> setHalfTimePrice(event.price)
            CreateCompanyEvent.onRegisterCompany -> {
                if (companyId != -1L) {
                    updateCompany()
                } else {
                    registerCompany()
                }
            }

            CreateCompanyEvent.onBackSelected -> emitEffect(CreateCompanyEffect.NavigateToBack)
        }
    }

    private var companyNameCheckJob: Job? = null
    private fun setCompanyName(name: String) {
        val isNotEmpty = validateForm.validateFieldNotEmpty(name)

        _formState.update {
            it.copy(
                companyName = name,
                companyNameIsValid = isNotEmpty,
                companyNameErrorMessage = if (isNotEmpty) null else ValidationError.NameCompanyRequired
            )
        }

        companyNameCheckJob?.cancel()

        if (isNotEmpty) {
            companyNameCheckJob = viewModelScope.launch {
                delay(500)
                val newError = validateCompanyExists(name)

                _formState.update {
                    it.copy(
                        companyNameIsValid = newError == null,
                        companyNameErrorMessage = newError
                    )
                }
                validateSubmit()
            }
        } else {
            validateSubmit()
        }
    }

    private fun setCurrencyType(currency: CurrencyType) {
        _formState.update {
            it.copy(
                currencyType = currency
            )
        }
    }

    private fun setFullTimePrice(precio: String) {
        val filteredPrice = filteredPrice(precio)

        val error = when {
            !validateForm.validateFieldNotEmpty(filteredPrice) -> ValidationError.EmptyField
            !validateForm.isValidPrice(filteredPrice) -> ValidationError.InvalidFormat
            !validateForm.isRelationPriceValid(
                filteredPrice,
                _formState.value.halfTimePrice
            ) -> ValidationError.FullPriceRelationError

            else -> null
        }

        _formState.update {
            it.copy(
                fullTimePrice = filteredPrice,
                fullTimePriceIsValid = error == null,
                fullTimePriceErrorMessage = error,
                halfTimePriceIsValid = error != ValidationError.HalfPriceRelationError
            )
        }

        validateSubmit()
    }

    private fun setHalfTimePrice(precio: String) {
        val filteredPrice = filteredPrice(precio)
        val error = when {
            !validateForm.validateFieldNotEmpty(filteredPrice) -> ValidationError.EmptyField
            !validateForm.isValidPrice(filteredPrice) -> ValidationError.InvalidFormat
            !validateForm.isRelationPriceValid(
                filteredPrice,
                _formState.value.halfTimePrice
            ) -> ValidationError.HalfPriceRelationError

            else -> null
        }

        _formState.update {
            it.copy(
                halfTimePrice = filteredPrice,
                halfTimePriceIsValid = error == null,
                halfTimePriceErrorMessage = error,
                fullTimePriceIsValid = error != ValidationError.FullPriceRelationError
            )
        }

        validateSubmit()
    }

    private fun validateSubmit() {
        _formState.update {
            it.copy(
                isButtonEnabled = validateForm.validateFields(
                    _formState.value.companyNameIsValid,
                    _formState.value.fullTimePriceIsValid,
                    _formState.value.halfTimePriceIsValid
                )
            )
        }
    }

    private fun emitEffect(effect: CreateCompanyEffect) {
        viewModelScope.launch {
            _uiEffect.emit(effect)
        }
    }

    private fun registerCompany() {
        val state = _formState.value
        _formState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = registerCompanyUseCase(
                name = state.companyName,
                currency = state.currencyType,
                fullDayPrice = state.fullTimePrice,
                halfDayPrice = state.halfTimePrice
            )

            _formState.update { it.copy(isLoading = false) }

            result.onSuccess {
                clearForm()
                emitEffect(CreateCompanyEffect.ShowMessage(R.string.create_company_success))
                emitEffect(CreateCompanyEffect.NavigateToBack)
            }
                .onFailure { emitEffect(CreateCompanyEffect.ShowMessage(R.string.create_company_error)) }
        }
    }

    private fun loadCompanyData() {
        Log.e("LOADCOMPANY", "El id de la compañia es: ${companyId}")
        viewModelScope.launch {
            getCompanyByIdUseCase(companyId).take(1).collect { company ->
                if (company != null){
                    _formState.update { state ->
                        state.copy(
                            companyName = company.name,
                            currencyType = company.currency,
                            fullTimePrice = company.fullTimePrice.toString(),
                            halfTimePrice = company.halfTimePrice.toString(),
                            companyNameIsValid = true,
                            fullTimePriceIsValid = true,
                            halfTimePriceIsValid = true
                        )
                    }
                    validateSubmit()
                }else{
                    Log.e("LOADCOMPANY", "compañía no encontrada")
                    emitEffect(CreateCompanyEffect.ShowMessage(R.string.company_not_found))
                    delay(100)
                    emitEffect(CreateCompanyEffect.NavigateToBack)
                }
            }
        }
    }

    private fun updateCompany() {
        val state = _formState.value
        _formState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            val result = updateCompanyUseCase(
                Company(
                    id = companyId,
                    name = state.companyName,
                    fullTimePrice = state.fullTimePrice.toDoubleOrNull() ?: 0.0,
                    halfTimePrice = state.halfTimePrice.toDoubleOrNull() ?: 0.0,
                    currency = state.currencyType
                )
            )
            _formState.update { it.copy(isLoading = false) }

            result.onSuccess {
                emitEffect(CreateCompanyEffect.ShowMessage(R.string.changes_saved_successfully))
                delay(100)
                emitEffect(CreateCompanyEffect.NavigateToBack)
            }.onFailure {
                emitEffect(CreateCompanyEffect.ShowMessage(R.string.error_saving_changes))
            }
        }
    }

    private fun clearForm() {
        _formState.value = CreateCompanyFormState()
    }

    private fun filteredPrice(texto: String): String {
        if (texto.isEmpty()) return ""
        return texto.filter { it.isDigit() || it == '.' }
    }

}
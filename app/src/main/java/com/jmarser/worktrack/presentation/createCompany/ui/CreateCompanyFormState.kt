package com.jmarser.worktrack.presentation.createCompany.ui

import com.jmarser.worktrack.core.error.ValidationError
import com.jmarser.worktrack.domain.model.CurrencyType

data class CreateCompanyFormState(
    val companyName: String = "",
    val companyNameErrorMessage: ValidationError? = null,
    val companyNameIsValid: Boolean? = null,
    val currencyType: CurrencyType = CurrencyType.EURO,
    val fullTimePrice: String = "",
    val fullTimePriceErrorMessage: ValidationError? = null,
    val fullTimePriceIsValid: Boolean? = null,
    val halfTimePrice: String = "",
    val halfTimePriceErrorMessage: ValidationError? = null,
    val halfTimePriceIsValid: Boolean? = null,
    val isLoading: Boolean = false,
    val isButtonEnabled: Boolean = false
)

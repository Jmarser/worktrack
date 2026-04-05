package com.jmarser.worktrack.presentation.createCompany.ui

import com.jmarser.worktrack.domain.model.CurrencyType

/**
 * Project: WorkTrack
 * File: CreateCompanyEvent.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/04/2026
 */

sealed interface CreateCompanyEvent {

    data class SetCompanyName(val name: String): CreateCompanyEvent
    data class SetFullTimePrice(val price: String): CreateCompanyEvent
    data class SetHalfTimePrice(val price: String): CreateCompanyEvent
    data class SetCurrencyType(val currency: CurrencyType): CreateCompanyEvent
    object onRegisterCompany: CreateCompanyEvent
    object onBackSelected: CreateCompanyEvent
}
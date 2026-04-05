package com.jmarser.worktrack.core.error

/**
 * Project: WorkTrack
 * File: ValidationError.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 04/04/2026
 */

sealed interface ValidationError {
    data object ErrorUnknown: ValidationError

    data object EmptyField: ValidationError
    data object InvalidFormat: ValidationError
    data object FullPriceRelationError: ValidationError
    data object HalfPriceRelationError: ValidationError

    // Pantalla Crear empresa
    data object NameCompanyRequired: ValidationError
    data object CompanyAlreadyExists: ValidationError
}
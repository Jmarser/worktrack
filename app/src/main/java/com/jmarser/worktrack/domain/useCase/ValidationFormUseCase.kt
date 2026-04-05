package com.jmarser.worktrack.domain.useCase

import com.jmarser.worktrack.core.domain.validation.ValidationForm
import javax.inject.Inject

/**
 * Project: WorkTrack
 * File: ValidationFormUseCase.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/04/2026
 */

class ValidationFormUseCase @Inject constructor(
    private val validationForm: ValidationForm
) {
    fun validateFieldNotEmpty(texto: String): Boolean = validationForm.validateFieldNotEmpty(texto)
    fun validateFields(vararg validations: Boolean?): Boolean = validationForm.validateFields(*validations)
    fun isValidPrice(precio: String): Boolean = validationForm.isValidPrice(precio)
    fun isRelationPriceValid(fullPrice: String, halfPrice: String): Boolean = validationForm.isHalfTimePriceValid(fullPrice, halfPrice)
}
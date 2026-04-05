package com.jmarser.worktrack.core.domain.validation

/**
 * Project: WorkTrack
 * File: ValidationFormImpl.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/04/2026
 */

class ValidationFormImpl: ValidationForm {
    override fun validateFieldNotEmpty(texto: String): Boolean = texto.isNotEmpty() && texto.isNotBlank()
    override fun validateFields(vararg validations: Boolean?): Boolean {
        val areAllNotNull = validations.none { it == null }
        val areAllTrue = validations.all {it == true }

        return areAllNotNull && areAllTrue
    }

    override fun isValidPrice(precio: String): Boolean {
        val number = precio.toDoubleOrNull()
        return number != null && number >= 0.0
    }

    override fun isHalfTimePriceValid(
        fullTimePrice: String,
        halfTimePrice: String
    ): Boolean {
        val full = fullTimePrice.toDoubleOrNull() ?: 0.0
        val half = halfTimePrice.toDoubleOrNull() ?: 0.0

        return half in 0.0..full
    }
}
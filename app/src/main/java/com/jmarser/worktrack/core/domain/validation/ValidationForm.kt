package com.jmarser.worktrack.core.domain.validation

/**
 * Project: WorkTrack
 * File: ValidationForm.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/04/2026
 */

interface ValidationForm {

    fun validateFieldNotEmpty(texto: String): Boolean
    fun validateFields(vararg validations: Boolean?): Boolean
    fun isValidPrice(precio: String): Boolean
    fun isHalfTimePriceValid(fullTimePrice: String, halfTimePrice: String): Boolean
}
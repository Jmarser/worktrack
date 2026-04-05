package com.jmarser.worktrack.domain.useCase

import com.jmarser.worktrack.core.error.ValidationError
import com.jmarser.worktrack.domain.repository.CompanyRepository
import javax.inject.Inject

/**
 * Project: WorkTrack
 * File: ValidateCompanyNameUseCase.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 05/04/2026
 */

class ValidateCompanyNameUseCase @Inject constructor(
    private val repository: CompanyRepository
) {

    suspend operator fun invoke(name: String): ValidationError?{
        val exists = repository.existsCompanyByName(name.trim())

        return if (exists){
            ValidationError.CompanyAlreadyExists
        }else{
            null
        }
    }
}
package com.jmarser.worktrack.domain.useCase

import com.jmarser.worktrack.domain.model.Company
import com.jmarser.worktrack.domain.model.CurrencyType
import com.jmarser.worktrack.domain.repository.CompanyRepository
import javax.inject.Inject

/**
 * Project: WorkTrack
 * File: RegisterCompanyUseCase.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 05/04/2026
 */

class RegisterCompanyUseCase @Inject constructor(
    private val repository: CompanyRepository
) {

    suspend operator fun invoke(
        name: String, currency: CurrencyType, fullDayPrice: String, halfDayPrice: String
    ): Result<Unit>{
        return try{
            val company = Company(
                id = 0,
                name = name.trim(),
                currency = currency,
                fullTimePrice = fullDayPrice.toDoubleOrNull() ?: 0.0,
                halfTimePrice = halfDayPrice.toDoubleOrNull() ?: 0.0,
                createdAt = System.currentTimeMillis()
            )
            repository.addCompany(company)
            Result.success(Unit)
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}
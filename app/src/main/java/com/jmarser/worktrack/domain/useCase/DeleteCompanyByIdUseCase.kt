package com.jmarser.worktrack.domain.useCase

import com.jmarser.worktrack.domain.model.Company
import com.jmarser.worktrack.domain.repository.CompanyRepository
import javax.inject.Inject

/**
 * Project: WorkTrack
 * File: DeleteCompanyById.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/04/2026
 */

class DeleteCompanyByIdUseCase @Inject constructor(
    private val repository: CompanyRepository
) {

    operator suspend fun invoke(companyId: Long): Result<Unit>{
        return try{
            repository.deleteCompanyById(companyId)
            Result.success(Unit)
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}
package com.jmarser.worktrack.domain.useCase

import com.jmarser.worktrack.domain.model.CompanyComplete
import com.jmarser.worktrack.domain.repository.CompanyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Project: WorkTrack
 * File: GetCompanyCompleteByIdUseCase.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 11/04/2026
 */

class GetCompanyCompleteByIdUseCase @Inject constructor(
    private val repository: CompanyRepository
) {
    operator fun invoke(companyId: Long): Flow<CompanyComplete?>{
        return repository.getCompanyComplete(companyId)
    }
}
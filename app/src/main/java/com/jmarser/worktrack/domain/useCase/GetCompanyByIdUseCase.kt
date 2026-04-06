package com.jmarser.worktrack.domain.useCase

import com.jmarser.worktrack.domain.model.Company
import com.jmarser.worktrack.domain.repository.CompanyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Project: WorkTrack
 * File: getCompanyByIdUseCase.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 06/04/2026
 */

class GetCompanyByIdUseCase @Inject constructor(
    private val repository: CompanyRepository
) {

    operator fun invoke(companyId: Long): Flow<Company?> {
        return repository.getCompanyById(companyId)
    }
}
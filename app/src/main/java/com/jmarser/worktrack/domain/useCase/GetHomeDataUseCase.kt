package com.jmarser.worktrack.domain.useCase

import com.jmarser.worktrack.domain.mapper.toUiModel
import com.jmarser.worktrack.domain.model.HomeData
import com.jmarser.worktrack.domain.repository.CompanyRepository
import com.jmarser.worktrack.presentation.companyList.model.HomeUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Project: WorkTrack
 * File: GetHomeDataUseCase.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/03/2026
 */

class GetHomeDataUseCase @Inject constructor(
    private val repository: CompanyRepository
) {
    operator fun invoke(): Flow<HomeUiModel>{
        return repository.getCompaniesSummary().map { list ->
            HomeData(
                companies = list,
                totalCompanies = list.size,
                totalGlobalPendingDays = list.sumOf { it.daysPending },
                totalGlobalPendingAmount = list.sumOf { it.amountPaid}
            ).toUiModel()
        }
    }
}
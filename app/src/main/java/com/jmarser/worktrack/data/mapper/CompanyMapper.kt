package com.jmarser.worktrack.data.mapper

import com.jmarser.worktrack.data.local.database.entity.CompanyEntity
import com.jmarser.worktrack.domain.model.Company
import com.jmarser.worktrack.domain.model.CurrencyType

/**
 * Project: WorkTrack
 * File: CompanyMapper.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

fun CompanyEntity.toDomain(): Company{
    return Company(
        id = this.id,
        name = this.name,
        currency = CurrencyType.valueOf(this.currency),
        fullTimePrice = this.fullTimePrice,
        halfTimePrice = this.halfTimePrice,
        createdAt = this.createdAt
    )
}

fun Company.toEntity(): CompanyEntity{
    return CompanyEntity(
        id = this.id,
        name = this.name,
        currency = this.currency.name,
        fullTimePrice = this.fullTimePrice,
        halfTimePrice = this.halfTimePrice,
        createdAt = this.createdAt
    )
}
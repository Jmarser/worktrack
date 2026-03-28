package com.jmarser.worktrack.data.mapper

import com.jmarser.worktrack.data.local.database.entity.CompanyEntity
import com.jmarser.worktrack.domain.model.Company

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
        fullTimePrice = this.fullTimePrice,
        halfTimePrice = this.halfTimePrice
    )
}

fun Company.toEntity(): CompanyEntity{
    return CompanyEntity(
        id = this.id,
        name = this.name,
        fullTimePrice = this.fullTimePrice,
        halfTimePrice = this.halfTimePrice
    )
}
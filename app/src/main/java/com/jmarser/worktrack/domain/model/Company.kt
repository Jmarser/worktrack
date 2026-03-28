package com.jmarser.worktrack.domain.model

data class Company(
    val id: Long = 0,
    val name: String,
    val fullTimePrice: Double? = null,
    val halfTimePrice: Double? = null
)

package com.jmarser.worktrack.domain.model

data class CompanyComplete(
    val company: Company,
    val workDays: List<WorkDayComplete>? = emptyList()
){
    val totalDaysWorked: Int
        get() = workDays?.size ?: 0

    val daysPendingToPay: Int
        get() = workDays?.count{ !it.workDay.isPaid }?: 0

    val totalAmountPaid: Double
        get() = workDays?.filter { it.workDay.isPaid }?.sumOf { it.workDay.amountPaid } ?: 0.0

    val totalAmountPending: Double
        get() = workDays?.filter { !it.workDay.isPaid }?.sumOf { it.workDay.amountPaid } ?: 0.0
}

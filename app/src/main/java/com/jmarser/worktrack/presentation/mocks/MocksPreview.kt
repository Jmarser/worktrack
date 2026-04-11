package com.jmarser.worktrack.presentation.mocks

import com.jmarser.worktrack.domain.model.Company
import com.jmarser.worktrack.domain.model.CompanyComplete
import com.jmarser.worktrack.domain.model.CurrencyType
import com.jmarser.worktrack.domain.model.Expanse
import com.jmarser.worktrack.domain.model.WorkDay
import com.jmarser.worktrack.domain.model.WorkDayComplete
import com.jmarser.worktrack.domain.model.WorkLocation
import java.util.Date

/**
 * Project: WorkTrack
 * File: MocksPreview.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 07/04/2026
 */

object CompanyMocks {

    // --- 1. Mocks de Gastos (Expanse) ---
    val sampleExpanse = Expanse(
        id = 1,
        workDayId = 101,
        description = "Material de oficina",
        amount = 15.50,
        isPaid = true
    )

    val expanseList = listOf(
        sampleExpanse,
        Expanse(2, 101, "Gasolina", 40.0, false)
    )

    // --- 2. Mocks de Ubicaciones (WorkLocation) ---
    val sampleLocation = WorkLocation(
        id = 1,
        workDayId = 101,
        name = "Sede Central - Madrid",
        description = "Planta 3, oficina 302"
    )

    val locationList = listOf(
        sampleLocation,
        WorkLocation(2, 101, "Sucursal Norte", "Almacén principal")
    )

    // --- 3. Mocks de Jornada Laboral (WorkDay) ---
    val sampleWorkDay = WorkDay(
        id = 101,
        companyId = 1,
        date = Date(), // Fecha actual
        isHalfTime = false,
        amountPaid = 120.0,
        isPaid = true,
        description = "Mantenimiento preventivo servidores"
    )

    // --- 4. Mock de WorkDayComplete (Unión de jornada + localizaciones + gastos) ---
    val sampleWorkDayComplete = WorkDayComplete(
        workDay = sampleWorkDay,
        locations = locationList,
        expanses = expanseList
    )

    val workDaysCompleteList = listOf(
        sampleWorkDayComplete,
        WorkDayComplete(
            workDay = sampleWorkDay.copy(id = 102, isHalfTime = true, amountPaid = 60.0, isPaid = false),
            locations = listOf(sampleLocation),
            expanses = emptyList()
        )
    )

    // --- 5. Mock de Company (Modelo base) ---
    val sampleCompany = Company(
        id = 1,
        name = "Tech Solutions S.L.",
        currency = CurrencyType.EURO, // Ajusta según tu enum
        fullTimePrice = 150.0,
        halfTimePrice = 80.0,
        createdAt = System.currentTimeMillis()
    )

    // --- 6. EL MOCK FINAL: CompanyComplete ---
    val sampleCompanyComplete = CompanyComplete(
        company = sampleCompany,
        workDays = workDaysCompleteList
    )

    // Versión para una empresa que acaba de ser creada (Sin jornadas)
    val emptyCompanyComplete = CompanyComplete(
        company = sampleCompany.copy(name = "Empresa Nueva"),
        workDays = emptyList()
    )
}
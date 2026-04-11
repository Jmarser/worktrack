package com.jmarser.worktrack.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.jmarser.worktrack.presentation.companyDetails.ui.CompanyDetails
import com.jmarser.worktrack.presentation.companyList.ui.CompanyListScreen
import com.jmarser.worktrack.presentation.createCompany.ui.CreateCompanyScreen

/**
 * Project: WorkTrack
 * File: NavGraph.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/03/2026
 */
 
@Composable
fun NavGraph(){

    val backStack: NavBackStack<NavKey> = rememberNavBackStack(Route.CompanyList)

    val entries = entryProvider <NavKey>{
        entry <Route.CompanyList>{
            CompanyListScreen(
                navigateToCreateCompany = {
                    backStack.add(Route.CreateCompany())
                },
                navigateToEditCompany = {id ->
                    Log.e("NAVEGACION_HACIA_EDITAR", "El id pasado para editar es: ${id}")
                    backStack.add(Route.CreateCompany(companyId = id))
                },
                navigateToCompanyDetails = {id ->
                    backStack.add(Route.CompanyDetails(companyId = id))
                }
            )
        }
        entry <Route.CreateCompany>{route ->
            Log.e("NAVEGACION_EN_EDITAR", "El id que llega a editar es: ${route.companyId}")
            CreateCompanyScreen(
                companyId = route.companyId,
                navigateToBack = {
                    backStack.removeLastOrNull()
                }
            )
        }
        entry <Route.CompanyDetails>{route ->
            CompanyDetails(
                companyId = route.companyId,
                navigateToBack = {
                    backStack.removeLastOrNull()
                },
                navigateToWorkDayDetails = {},
                navigateToCreateWorkDay = {}
            )
        }
    }

    NavDisplay(
        backStack = backStack,
        entryProvider = entries,
        onBack = {backStack.removeLastOrNull()}
    )
}
package com.jmarser.worktrack.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
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
                    backStack.add(Route.CreateCompany)
                }
            )
        }
        entry <Route.CreateCompany>{
            CreateCompanyScreen(
                navigateToBack = {
                    backStack.removeLastOrNull()
                }
            )
        }
    }

    NavDisplay(
        backStack = backStack,
        entryProvider = entries,
        onBack = {backStack.removeLastOrNull()}
    )
}
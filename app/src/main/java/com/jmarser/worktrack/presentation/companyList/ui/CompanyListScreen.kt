package com.jmarser.worktrack.presentation.companyList.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jmarser.worktrack.core.presentation.components.AppBar
import com.jmarser.worktrack.core.presentation.components.AppImages
import com.jmarser.worktrack.core.presentation.screens.EmptyScreen
import com.jmarser.worktrack.core.presentation.screens.ErrorScreen
import com.jmarser.worktrack.core.presentation.screens.LoadingScreen
import com.jmarser.worktrack.presentation.companyList.components.CompanyItem
import com.jmarser.worktrack.presentation.companyList.components.HeaderResumenSection
import com.jmarser.worktrack.ui.theme.MyAppTheme

@Composable
fun CompanyListScreen(
    modifier: Modifier = Modifier,
    viewModel: CompanyListViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                CompanyListEffect.NavigateToCreateCompany -> TODO()
                is CompanyListEffect.NavigateToDeleteCompany -> TODO()
                is CompanyListEffect.NavigateToDetailsCompany -> TODO()
                is CompanyListEffect.NavigateToEditCompany -> TODO()
                is CompanyListEffect.ShowMessage -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            AppBar(
                modifier = Modifier,
                title = "Empresas",
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->

        when (val state = uiState) {
            CompanyListState.Loading -> {
                LoadingScreen()
            }

            CompanyListState.Empty -> {
                EmptyScreen(
                    modifier = modifier.padding(paddingValues),
                    message = "No hay empresas disponibles\nCree una empresa."
                )
            }

            CompanyListState.Idle -> {}
            is CompanyListState.Failure -> {
                ErrorScreen(
                    modifier = modifier.padding(paddingValues),
                    message = state.message,
                    buttonTxt = "Reintentar",
                    icon = AppImages.ic_refresh
                )
            }

            is CompanyListState.Success -> {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LazyColumn(
                    ) {
                        item {
                            HeaderResumenSection(modifier = Modifier, state.data)
                        }
                        items(
                            items = state.data.companies,
                            key = { it.id }
                        ) { company ->
                            CompanyItem(
                                company = company,
                                onEditClick = { companyId ->

                                },
                                onDeleteClick = { companyId ->

                                }
                            )
                        }
                    }
                }
            }
        }

    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun CompanyListScreenPreview() {
    MyAppTheme() {
        CompanyListScreen(modifier = Modifier)
    }
}



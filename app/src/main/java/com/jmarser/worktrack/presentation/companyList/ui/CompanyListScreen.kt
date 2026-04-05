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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jmarser.worktrack.R
import com.jmarser.worktrack.core.presentation.components.AppBar
import com.jmarser.worktrack.core.presentation.components.AppImages
import com.jmarser.worktrack.core.presentation.components.CardWithShimmer
import com.jmarser.worktrack.core.presentation.screens.EmptyScreen
import com.jmarser.worktrack.core.presentation.screens.ErrorScreen
import com.jmarser.worktrack.core.presentation.screens.LoadingScreen
import com.jmarser.worktrack.presentation.companyList.components.CompanyItem
import com.jmarser.worktrack.presentation.companyList.components.HeaderResumenSection
import com.jmarser.worktrack.presentation.error.asString
import com.jmarser.worktrack.ui.theme.MyAppTheme

@Composable
fun CompanyListScreen(
    modifier: Modifier = Modifier,
    viewModel: CompanyListViewModel = hiltViewModel(),
    navigateToCreateCompany: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                CompanyListEffect.NavigateToCreateCompany -> navigateToCreateCompany()
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
                title = stringResource(R.string.companies),
                showOnBack = false,
                showSettings = true,
                showAddIcon = true,
                showFilters = false,
                onCreatedSelected = {
                    viewModel.onEvent(CompanyListEvent.onClickCreateCompany)
                }
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->

        val contentModifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()

        when (val state = uiState) {
            CompanyListState.Loading -> {
                CompanyListLoading(
                    modifier = contentModifier
                )
            }

            CompanyListState.Empty -> {
                EmptyScreen(
                    modifier = contentModifier,
                    message = stringResource(R.string.list_companies_empty)
                )
            }

            CompanyListState.Idle -> {
                CompanyListLoading(
                    modifier = contentModifier
                )
            }

            is CompanyListState.Failure -> {
                ErrorScreen(
                    modifier = contentModifier,
                    message = state.message.asString(),
                    buttonTxt = stringResource(R.string.retry),
                    icon = AppImages.ic_refresh,
                    onRetryClick = {}
                )
            }

            is CompanyListState.Success -> {
                Column(
                    modifier = contentModifier,
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
        CompanyListScreen(
            modifier = Modifier,
            navigateToCreateCompany = {}
        )
    }
}



package com.jmarser.worktrack.presentation.companyList.ui


import android.util.Log
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jmarser.worktrack.R
import com.jmarser.worktrack.core.presentation.components.AppBar
import com.jmarser.worktrack.core.presentation.components.AppImages
import com.jmarser.worktrack.core.presentation.components.CustomConfirmDialog
import com.jmarser.worktrack.core.presentation.screens.EmptyScreen
import com.jmarser.worktrack.core.presentation.screens.ErrorScreen
import com.jmarser.worktrack.presentation.companyList.components.CompanyItem
import com.jmarser.worktrack.presentation.companyList.components.HeaderResumenSection
import com.jmarser.worktrack.presentation.error.asString
import com.jmarser.worktrack.ui.theme.MyAppTheme

@Composable
fun CompanyListScreen(
    modifier: Modifier = Modifier,
    viewModel: CompanyListViewModel = hiltViewModel(),
    navigateToCreateCompany: () -> Unit,
    navigateToEditCompany: (Long) -> Unit,
    navigateToCompanyDetails: (Long) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
    val deleteDialogState by viewModel.deleteDialogState.collectAsStateWithLifecycle()
    var pendingMessageResId by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                CompanyListEffect.NavigateToCreateCompany -> navigateToCreateCompany()
                is CompanyListEffect.NavigateToDetailsCompany -> navigateToCompanyDetails(effect.companyId)
                is CompanyListEffect.NavigateToEditCompany -> {
                    navigateToEditCompany(effect.companyId)
                }
                is CompanyListEffect.ShowMessage -> {
                    pendingMessageResId = effect.resId
                }
            }
        }
    }

    val messageToShow = if (pendingMessageResId != 0) stringResource(pendingMessageResId) else ""
    LaunchedEffect(pendingMessageResId) {
        if (pendingMessageResId != 0){
            snackbarHostState.showSnackbar(messageToShow)
            pendingMessageResId = 0
        }
    }

    deleteDialogState?.let{companySummary ->
        CustomConfirmDialog(
            textTitle = stringResource(R.string.delete_company),
            textMessage = stringResource(R.string.msg_delete_company, companySummary.name),
            textBtnConfirm = stringResource(R.string.delete),
            textBtnCancel = stringResource(R.string.cancel),
            onConfirm = {
                viewModel.onEvent(CompanyListEvent.onClickDeleteCompany(companySummary.id))
            },
            onCancel = {
                viewModel.onEvent(CompanyListEvent.ToggleDeleteDialogState(null))
            },
            onDismiss = {
                viewModel.onEvent(CompanyListEvent.ToggleDeleteDialogState(null))
            }
        )
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
                    title = "No hay empresas disponibles",
                    description = "Empieza agregando tu primera empresa.",
                    iconScreen = AppImages.ic_company,
                    buttonText = "Crear ahora",
                    onButtonClick = {
                        viewModel.onEvent(CompanyListEvent.onClickCreateCompany)
                    }
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
                    title = state.message.asString(),
                    description = "No hemos podido cargar las empresas.",
                    buttonTxt = stringResource(R.string.retry),
                    iconButton = AppImages.ic_refresh,
                    onRetryClick = {
                        viewModel.onEvent(CompanyListEvent.onRetry)
                    }
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
                            Log.e("LISTACOMPAÑIAS", "El id de la compañía es: ${company.id}")
                            CompanyItem(
                                company = company,
                                onEditClick = { companyId ->
                                    viewModel.onEvent(CompanyListEvent.onClickEditCompany(companyId))
                                },
                                onDeleteClick = { companySummary ->
                                    viewModel.onEvent(CompanyListEvent.ToggleDeleteDialogState(companySummary))
                                },
                                onDetailsClick = {companyId ->
                                    viewModel.onEvent(CompanyListEvent.onClickNavigateToDetails(companyId))
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
            navigateToCreateCompany = {},
            navigateToEditCompany = {},
            navigateToCompanyDetails = {}
        )
    }
}



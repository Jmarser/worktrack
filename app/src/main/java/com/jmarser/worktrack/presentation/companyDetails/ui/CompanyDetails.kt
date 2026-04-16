package com.jmarser.worktrack.presentation.companyDetails.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jmarser.worktrack.R
import com.jmarser.worktrack.core.presentation.components.AppBar
import com.jmarser.worktrack.core.presentation.components.AppImages
import com.jmarser.worktrack.core.presentation.components.VerticalSpaceNormal
import com.jmarser.worktrack.core.presentation.screens.EmptyScreen
import com.jmarser.worktrack.core.presentation.screens.ErrorScreen
import com.jmarser.worktrack.core.presentation.screens.LoadingScreen
import com.jmarser.worktrack.presentation.companyDetails.components.EmptyWorkDays
import com.jmarser.worktrack.presentation.companyDetails.components.LoadingCompanyDetails
import com.jmarser.worktrack.presentation.companyDetails.components.ResumenDetailsCard
import com.jmarser.worktrack.presentation.companyDetails.components.ResumenWorkDay
import com.jmarser.worktrack.presentation.companyList.ui.CompanyListEvent
import com.jmarser.worktrack.presentation.error.asString
import com.jmarser.worktrack.presentation.mocks.CompanyMocks.workDaysCompleteList
import com.jmarser.worktrack.presentation.utils.toFullMonthYear
import com.jmarser.worktrack.ui.theme.MyAppTheme
import com.jmarser.worktrack.ui.theme.appDimens
import java.util.Date

@Composable
fun CompanyDetails(
    modifier: Modifier = Modifier,
    companyId: Long,
    viewModel: CompanyDetailsViewModel = hiltViewModel<CompanyDetailsViewModel, CompanyDetailsViewModel.Factory>(
        key = companyId.toString()
    ) { factory ->
        factory.create(companyId)
    },
    navigateToBack: () -> Unit,
    navigateToWorkDayDetails: () -> Unit,
    navigateToCreateWorkDay: () -> Unit
) {

    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppBar(
                modifier = modifier,
                title = stringResource(R.string.workdays_top_bar_title),
                showOnBack = true,
                showAddIcon = true,
                showFilters = true,
                showSettings = true,
                onCreatedSelected = {
                    navigateToCreateWorkDay()
                },
                onBackSelected = {
                    navigateToBack()
                },
                onSettingsSelected = {},
                onFiltersSelected = {}
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
            CompanyDetailsState.Idle, CompanyDetailsState.Loading -> {
                LoadingCompanyDetails(modifier = contentModifier)
            }

            CompanyDetailsState.Empty -> {
                EmptyScreen(
                    modifier = contentModifier,
                    title = stringResource(R.string.empty_screen_title_companies),
                    description = stringResource(R.string.empty_screen_desc_companies),
                    iconScreen = AppImages.ic_company,
                    buttonText = stringResource(R.string.empty_screen_action_button),
                    onButtonClick = {

                    }
                )
            }

            is CompanyDetailsState.Failure -> {
                ErrorScreen(
                    modifier = contentModifier,
                    title = state.message.asString(),
                    description = stringResource(R.string.empty_screen_action_button),
                    buttonTxt = stringResource(R.string.action_retry),
                    iconButton = AppImages.ic_refresh,
                    onRetryClick = {

                    }
                )
            }

            is CompanyDetailsState.Success -> {
                LazyColumn(
                    modifier = contentModifier
                ) {
                    item {
                        ResumenDetailsCard(
                            modifier = Modifier
                                .padding(appDimens.paddingMedium),
                            companyName = state.data.company.name,
                            totalDays = state.totalDays,
                            pendingDays = state.totalDaysPending,
                            totalPaid = "${state.totalPaid.toString()} €",
                            pendingAmount = "${state.totalPending.toString()} €"
                        )
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = appDimens.paddingLarge,
                                    vertical = appDimens.paddingNormal
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.workdays_list_header),
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,

                                )
                            Text(
                                text = Date().toFullMonthYear(),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    if (state.data.workDays.isNullOrEmpty()) {
                        item {
                            EmptyWorkDays()
                        }
                    } else {
                        items(
                            items = state.data.workDays,
                            key = { it.workDay.id }
                        ) { workDay ->
                            ResumenWorkDay(
                                workDay = workDay.workDay
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
fun CompanyDetailsPreview() {
    MyAppTheme() {
        CompanyDetails(
            modifier = Modifier,
            companyId = 0,
            navigateToBack = {},
            navigateToWorkDayDetails = {},
            navigateToCreateWorkDay = {}
        )
    }
}

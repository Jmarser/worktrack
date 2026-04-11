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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.jmarser.worktrack.R
import com.jmarser.worktrack.core.presentation.components.AppBar
import com.jmarser.worktrack.core.presentation.components.VerticalSpaceNormal
import com.jmarser.worktrack.presentation.companyDetails.components.ResumenDetailsCard
import com.jmarser.worktrack.presentation.companyDetails.components.ResumenWorkDay
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
    ){factory ->
        factory.create(companyId)
    },
    navigateToBack: () -> Unit,
    navigateToWorkDayDetails: () -> Unit,
    navigateToCreateWorkDay: () -> Unit
) {

    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            AppBar(
                modifier = modifier,
                title = stringResource(R.string.days_worked),
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

        Column(
            modifier = contentModifier
        ) {
            ResumenDetailsCard(
                modifier = Modifier
                    .padding(appDimens.paddingMedium)
            )

            VerticalSpaceNormal()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = appDimens.paddingMedium,
                        vertical = appDimens.paddingNormal
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.recent_activity),
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                items(
                    items = workDaysCompleteList
                ){workDay ->
                    ResumenWorkDay(
                        workDay = workDay.workDay
                    )
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

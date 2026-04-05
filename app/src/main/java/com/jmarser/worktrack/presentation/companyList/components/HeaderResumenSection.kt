package com.jmarser.worktrack.presentation.companyList.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.worktrack.R
import com.jmarser.worktrack.presentation.companyList.model.HomeUiModel
import com.jmarser.worktrack.presentation.companyList.model.mockCompanies
import com.jmarser.worktrack.presentation.companyList.model.mockHomeUiState
import com.jmarser.worktrack.ui.theme.MyAppTheme
import com.jmarser.worktrack.ui.theme.appDimens

@Composable
fun HeaderResumenSection(
    modifier: Modifier = Modifier,
    data: HomeUiModel
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(appDimens.paddingMedium),
        verticalArrangement = Arrangement.spacedBy(appDimens.paddingSmall)
    ) {
        Text(
            text = stringResource(R.string.general_summary),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = stringResource(R.string.my_contributions),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )

        ResumenCard(
            title = stringResource(R.string.active_companies),
            message = data.totalCompanies,
            colorMessage = MaterialTheme.colorScheme.onPrimaryContainer
        )
        ResumenCard(
            title = stringResource(R.string.days_outstanding),
            message = data.globalPendingDays,
            colorMessage = MaterialTheme.colorScheme.error
        )
        ResumenCard(
            title = stringResource(R.string.amount_due),
            message = "${data.totalPendingAmount} €",
            colorMessage = MaterialTheme.colorScheme.error
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun HeaderResumenSectionPreview() {
    MyAppTheme() {
        HeaderResumenSection(data = mockHomeUiState)
    }
}

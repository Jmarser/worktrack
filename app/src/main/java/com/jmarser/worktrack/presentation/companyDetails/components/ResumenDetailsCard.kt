package com.jmarser.worktrack.presentation.companyDetails.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmarser.worktrack.R
import com.jmarser.worktrack.core.presentation.components.VerticalSpaceLarge
import com.jmarser.worktrack.core.presentation.components.VerticalSpaceNormal
import com.jmarser.worktrack.ui.theme.MyAppTheme
import com.jmarser.worktrack.ui.theme.appDimens

@Composable
fun ResumenDetailsCard(
    modifier: Modifier = Modifier,
    companyName: String = "Tech Solutions",
    totalDays: Int = 15,
    pendingDays: Int = 2,
    totalPaid: String = "1250 €",
    pendingAmount: String = "250 €"
) {

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(appDimens.paddingNormal)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.company_upper),
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            Text(
                text = companyName,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            VerticalSpaceNormal()

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(appDimens.paddingNormal)
            ) {
                StatusBox(
                    modifier = Modifier
                        .weight(1f),
                    label = stringResource(R.string.total_days_upper),
                    value = totalDays.toString(),
                    containerColor = Color(0xFFF1F3F5),
                    contentColor = Color.Black,
                    isRight = false
                )
                StatusBox(
                    modifier = Modifier
                        .weight(1f),
                    label = stringResource(R.string.days_remaining_upper),
                    value = pendingDays.toString(),
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.error,
                    isRight = true
                )
            }

            VerticalSpaceLarge()
            HorizontalDivider(
                color = Color(0xFFF1F3F5),
                thickness = 1.dp
            )
            VerticalSpaceNormal()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(appDimens.paddingNormal),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                EconomicValue(
                    label = stringResource(R.string.total_paid_upper),
                    value = totalPaid,
                    isError = false
                )
                EconomicValue(
                    label = stringResource(R.string.pending_upper),
                    value = pendingAmount,
                    isError = true
                )
            }
        }
    }
}

@Preview(
    showSystemUi = false,
    showBackground = true
)
@Composable
fun ResumenDetailsCardPreview() {
    MyAppTheme() {
        ResumenDetailsCard(modifier = Modifier)
    }
}

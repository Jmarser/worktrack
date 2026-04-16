package com.jmarser.worktrack.presentation.companyDetails.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmarser.worktrack.R
import com.jmarser.worktrack.domain.model.WorkDay
import com.jmarser.worktrack.presentation.mocks.CompanyMocks.sampleWorkDay
import com.jmarser.worktrack.presentation.utils.toDayString
import com.jmarser.worktrack.presentation.utils.toMonthAbbr
import com.jmarser.worktrack.ui.theme.MyAppTheme
import com.jmarser.worktrack.ui.theme.appDimens

@Composable
fun ResumenWorkDay(
    modifier: Modifier = Modifier,
    workDay: WorkDay,
) {
    val isPending = workDay.isPaid

    // Configuración de colores según estado
    val statusContainerColor = if (isPending) Color(0xFFFEE2E2) else Color(0xFFDCFCE7)
    val statusContentColor = if (isPending) Color(0xFF991B1B) else Color(0xFF166534)
    val statusLabel = stringResource( if (isPending) R.string.workdays_item_status_pending else R.string.workdays_item_status_paid)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = appDimens.paddingMedium,
                vertical = appDimens.paddingTiny
            ),
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(appDimens.paddingNormal),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .width(appDimens.buttonHeightNormal)
                    .clip(MaterialTheme.shapes.medium)
                    .background(color = statusContainerColor)
                    .padding(appDimens.paddingNormal),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = workDay.date.toMonthAbbr(),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = statusContentColor
                )
                Text(
                    text = workDay.date.toDayString(),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Black,
                    color = statusContentColor
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = appDimens.paddingNormal),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = workDay.description ?: stringResource(R.string.error_unknown),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
            }
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(appDimens.paddingSmall),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${workDay.amountPaid.toInt()} €",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier
                        .background(
                            color = statusContainerColor,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(
                            horizontal = appDimens.paddingSmall,
                            vertical = appDimens.paddingTiny
                        ),
                    text = statusLabel,
                    style = MaterialTheme.typography.labelSmall,
                    color = statusContentColor
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
fun ResumenWorkDayPreview() {
    MyAppTheme() {
        ResumenWorkDay(
            modifier = Modifier,
            workDay = sampleWorkDay
        )
    }
}

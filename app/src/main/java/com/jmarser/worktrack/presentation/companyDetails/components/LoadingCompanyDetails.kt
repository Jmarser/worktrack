package com.jmarser.worktrack.presentation.companyDetails.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmarser.worktrack.R
import com.jmarser.worktrack.core.presentation.components.CardWithShimmer
import com.jmarser.worktrack.presentation.utils.toFullMonthYear
import com.jmarser.worktrack.ui.theme.MyAppTheme
import com.jmarser.worktrack.ui.theme.appDimens
import java.util.Date

@Composable
fun LoadingCompanyDetails(
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
    ) {
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(appDimens.paddingNormal),
                shape = MaterialTheme.shapes.small,
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = appDimens.cardElevationNormal)
            ) {
                Column(
                    modifier = Modifier
                        .padding(appDimens.paddingNormal)
                        .fillMaxWidth()
                ) {
                    CardWithShimmer(cardHeight = appDimens.cardHeightNormal)

                    CardWithShimmer(cardHeight = appDimens.cardHeightMedium)
                    HorizontalDivider()
                    CardWithShimmer(cardHeight = appDimens.cardHeightNormal)
                }
            }
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
        items(8){
            CardWithShimmer(
                modifier = Modifier
                    .padding(horizontal = appDimens.paddingNormal),
                cardHeight = appDimens.cardHeightMedium
            )
        }
    }
    Column(
        modifier = modifier
    ) {

    }

}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun LoadingCompanyDetailsPreview() {
    MyAppTheme() {
        LoadingCompanyDetails(modifier = Modifier)
    }
}

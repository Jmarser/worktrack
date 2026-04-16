package com.jmarser.worktrack.presentation.companyList.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.worktrack.R
import com.jmarser.worktrack.core.presentation.components.CardWithShimmer
import com.jmarser.worktrack.ui.theme.MyAppTheme
import com.jmarser.worktrack.ui.theme.appDimens

@Composable
fun CompanyListLoading(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(appDimens.paddingMedium),
        verticalArrangement = Arrangement.spacedBy(appDimens.paddingSmall)
    ) {
        LazyColumn() {
            item {
                Text(
                    text = stringResource(R.string.companies_header_label),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            items(3){
                CardWithShimmer(cardHeight = appDimens.cardHeightNormal)
            }

            items(4){
                CardWithShimmer(cardHeight = appDimens.cardHeightLarge)
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun CompanyListLoadingPreview() {
    MyAppTheme() {
        CompanyListLoading(modifier = Modifier)
    }
}

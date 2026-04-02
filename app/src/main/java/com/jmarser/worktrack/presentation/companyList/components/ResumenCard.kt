package com.jmarser.worktrack.presentation.companyList.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.worktrack.ui.theme.MyAppTheme
import com.jmarser.worktrack.ui.theme.appDimens

@Composable
fun ResumenCard(
    modifier: Modifier = Modifier,
    title: String,
    message: String,
    colorMessage: Color
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = appDimens.cardElevationNormal)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = appDimens.paddingNormal,
                    horizontal = appDimens.paddingMedium
                ),
            verticalArrangement = Arrangement.spacedBy(appDimens.paddingTiny)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = message,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = colorMessage
            )
        }
    }
}

@Preview(
    showSystemUi = false,
    showBackground = true
)
@Composable
fun ResumenCardPreview() {
    MyAppTheme() {
        ResumenCard(
            title = "Empresas Activas",
            message = "04",
            colorMessage = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

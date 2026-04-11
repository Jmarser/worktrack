package com.jmarser.worktrack.presentation.companyList.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmarser.worktrack.core.presentation.components.VerticalSpaceLarge
import com.jmarser.worktrack.core.presentation.components.VerticalSpaceNormal
import com.jmarser.worktrack.domain.model.Company
import com.jmarser.worktrack.domain.model.CompanySummary
import com.jmarser.worktrack.presentation.companyList.model.mockCompanySummary
import com.jmarser.worktrack.ui.theme.MyAppTheme
import com.jmarser.worktrack.ui.theme.appDimens

@Composable
fun CompanyItem(
    modifier: Modifier = Modifier,
    company: CompanySummary,
    onEditClick: (Long) -> Unit,
    onDeleteClick: (CompanySummary) -> Unit,
    onDetailsClick: (Long) -> Unit
) {

    val bandera = company.daysPending > 0

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(appDimens.paddingMedium)
    ){
        Box(
            modifier = Modifier
                .width(150.dp)
                .fillMaxHeight()
                .background(
                    color = if (bandera) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.tertiary,
                    shape = MaterialTheme.shapes.extraLarge
                )
                .align(Alignment.CenterStart)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .offset(x = appDimens.paddingNormal)
                .padding(end = appDimens.paddingNormal)
                .clickable{
                    onDetailsClick(company.id)
                }
                .background(
                    color = Color.Transparent
                ),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = appDimens.cardElevationNormal)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(appDimens.paddingMedium)
            ) {
                Text(
                    text = company.name,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Total días trabajados: ${company.totalDaysWorked}",
                    style = MaterialTheme.typography.bodySmall
                )
                VerticalSpaceNormal()
                Text(
                    modifier = modifier
                        .background(
                            color = if (bandera) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.tertiaryContainer,
                            shape = MaterialTheme.shapes.large
                        )
                        .padding(horizontal = appDimens.paddingNormal),
                    text = "* DÍAS PENDIENTES DE COBRO: ${company.daysPending}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (bandera) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.tertiary
                )
                VerticalSpaceLarge()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = appDimens.paddingNormal),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = {
                            onEditClick(company.id)
                        }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(16.dp),
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                            )
                            Text(
                                modifier = Modifier
                                    .padding(start = appDimens.paddingTiny),
                                text = "Editar"
                            )
                        }
                    }
                    TextButton(
                        onClick = {
                            onDeleteClick(company)
                        }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(16.dp),
                                imageVector = Icons.Rounded.DeleteOutline,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.error
                            )
                            Text(
                                modifier = Modifier
                                    .padding(start = appDimens.paddingTiny),
                                text = "Eliminar",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    showSystemUi = false,
    showBackground = true
)
@Composable
fun CompanyItemPreview() {
    MyAppTheme() {
        CompanyItem(
            modifier = Modifier,
            company = mockCompanySummary,
            onEditClick = {},
            onDeleteClick = {},
            onDetailsClick = {}
        )
    }
}

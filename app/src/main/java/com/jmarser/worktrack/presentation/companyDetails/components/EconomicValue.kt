package com.jmarser.worktrack.presentation.companyDetails.components


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.worktrack.ui.theme.MyAppTheme

@Composable
fun EconomicValue(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    isError: Boolean,
    alignment: Alignment.Horizontal = Alignment.Start
) {
    Column(
        horizontalAlignment = alignment
    ){
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = if (isError) MaterialTheme.colorScheme.error else Color.Gray
        )
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Black,
            color = if (isError) MaterialTheme.colorScheme.error else Color.Black
        )
    }
}

@Preview(
    showSystemUi = false,
    showBackground = true
)
@Composable
fun EconomicValuePreview() {
    MyAppTheme() {
        EconomicValue(
            modifier = Modifier,
            label = "TOTAL COBRADO",
            value = "250 €",
            isError = true
        )
    }
}

package com.jmarser.worktrack.core.presentation.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.worktrack.core.presentation.components.AppImages
import com.jmarser.worktrack.core.presentation.components.ButtonWithPb
import com.jmarser.worktrack.ui.theme.MyAppTheme
import com.jmarser.worktrack.ui.theme.appDimens

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String,
    buttonTxt: String? = null,
    icon: ImageVector? = null
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(appDimens.paddingSmall)
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.tertiary
            )
            ButtonWithPb(
                iconStart = icon,
                label = buttonTxt,
                value = Unit,
                onClick = {}
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun ErrorScreenPreview() {
    MyAppTheme() {
        ErrorScreen(
            modifier = Modifier,
            message = "Error inesperado",
            buttonTxt = "Pulsar aquí",
            icon = AppImages.ic_refresh
        )
    }
}

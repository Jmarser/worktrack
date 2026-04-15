package com.jmarser.worktrack.core.presentation.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmarser.worktrack.core.presentation.components.AppImages
import com.jmarser.worktrack.core.presentation.components.ButtonWithPb
import com.jmarser.worktrack.core.presentation.components.HorizontalSpaceNormal
import com.jmarser.worktrack.core.presentation.components.VerticalSpaceExtraLarge
import com.jmarser.worktrack.core.presentation.components.VerticalSpaceLarge
import com.jmarser.worktrack.core.presentation.components.VerticalSpaceMedium
import com.jmarser.worktrack.core.presentation.components.VerticalSpaceNormal
import com.jmarser.worktrack.ui.theme.MyAppTheme
import com.jmarser.worktrack.ui.theme.appDimens

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    title: String = "Algo ha salido mal",
    description: String = "No hemos podido cargar la información. Pro favor, Comprueba tu conexión e intentalo de nuevo.",
    buttonTxt: String = "Reintentar",
    iconButton: ImageVector? = AppImages.ic_refresh,
    onRetryClick: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(
                    width = 160.dp,
                    height = 140.dp
                )
                .clip(MaterialTheme.shapes.extraLarge)
                .background(
                    color = Color.Black.copy(alpha = 0.05f)
                ),
            contentAlignment = Alignment.Center
        ){
            Box(Modifier.fillMaxSize().background(Color(0xFFEAECF0)))
            Icon(
                imageVector = Icons.Default.WifiOff,
                contentDescription = null,
                modifier = Modifier.size(72.dp),
                tint = MaterialTheme.colorScheme.primary // El azul profundo de tu paleta
            )
        }

        VerticalSpaceLarge()

        Text(
            text = "ESTADO DEL SISTEMA".uppercase(),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary, // Usamos azul para este label
            letterSpacing = 1.sp
        )
        
        VerticalSpaceNormal()

        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )

        VerticalSpaceMedium()

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = appDimens.paddingXL)
        )

        VerticalSpaceExtraLarge()

        Button(
            onClick = onRetryClick,
            modifier = Modifier.height(appDimens.buttonHeightNormal),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            contentPadding = PaddingValues(horizontal = appDimens.paddingLarge)
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            HorizontalSpaceNormal()
            Text(
                text = buttonTxt,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

/*        Surface(
            modifier = Modifier
                .size(80.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.errorContainer.copy(alpha = .4f)
        ) {
            Icon(
                imageVector = AppImages.ic_error_screen,
                contentDescription = null,
                modifier = Modifier
                    .padding(appDimens.paddingLarge)
                    .fillMaxSize(),
                tint = MaterialTheme.colorScheme.error
            )
        }
        VerticalSpaceLarge()
        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        subMessage?.let {
            VerticalSpaceNormal()
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = appDimens.paddingXL)
            )
        }
        VerticalSpaceLarge()
        ButtonWithPb(
            iconStart = iconButton,
            label = buttonTxt,
            value = Unit,
            onClick = { onRetryClick() }
        )*/

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
            title = "Error inesperado",
            buttonTxt = "Pulsar aquí",
            iconButton = AppImages.ic_refresh,
            onRetryClick = {},
            description = "No hemos podido cargar la información solicitada."
        )
    }
}

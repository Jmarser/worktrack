package com.jmarser.worktrack.presentation.companyDetails.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.EventNote
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmarser.worktrack.R
import com.jmarser.worktrack.core.presentation.components.AppImages
import com.jmarser.worktrack.core.presentation.components.HorizontalSpaceNormal
import com.jmarser.worktrack.core.presentation.components.VerticalSpaceExtraLarge
import com.jmarser.worktrack.core.presentation.components.VerticalSpaceLarge
import com.jmarser.worktrack.core.presentation.components.VerticalSpaceNormal
import com.jmarser.worktrack.core.presentation.components.VerticalSpaceSmall
import com.jmarser.worktrack.ui.theme.MyAppTheme
import com.jmarser.worktrack.ui.theme.appDimens

@Composable
fun EmptyWorkDays(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier
                .size(80.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = .4f)
        ) {
            Icon(
                imageVector = AppImages.ic_empty_work_day,
                contentDescription = null,
                modifier = Modifier
                    .padding(appDimens.paddingLarge)
                    .fillMaxSize(),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        VerticalSpaceLarge()

        Text(
            text = stringResource(R.string.no_recent_activity),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        VerticalSpaceNormal()

        Text(
            text = stringResource(R.string.msg_no_recent_activity),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = appDimens.paddingXL)
        )

        VerticalSpaceExtraLarge()

        Button(
            onClick = {

            },
            modifier = Modifier.height(56.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            Text(
                text = "Agregar día",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            HorizontalSpaceNormal()
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Preview(
    showSystemUi = false,
    showBackground = true
)
@Composable
fun EmptyWorkDaysPreview() {
    MyAppTheme() {
        EmptyWorkDays(modifier = Modifier)
    }
}

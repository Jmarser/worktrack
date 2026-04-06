package com.jmarser.worktrack.core.presentation.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jmarser.worktrack.R
import com.jmarser.worktrack.ui.theme.MyAppTheme
import com.jmarser.worktrack.ui.theme.appDimens

@Composable
fun CustomConfirmDialog(
    modifier: Modifier = Modifier,
    textTitle: String?,
    textMessage: String,
    textBtnConfirm: String? = stringResource(id = R.string.accept),
    textBtnCancel: String? = stringResource(id = R.string.cancel),
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    onDismiss: () -> Unit
) {

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth()
                .padding(appDimens.paddingNormal),
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary
            )
        ) {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(appDimens.paddingNormal),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!textTitle.isNullOrEmpty()){
                    VerticalSpaceNormal()
                    Text(
                        text = textTitle,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                VerticalSpaceNormal()

                Text(
                    text = textMessage,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )

                VerticalSpaceNormal()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = appDimens.paddingNormal),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(
                        onClick = {
                            onCancel()
                        }
                    ) {
                        Text(
                            text = textBtnCancel ?: stringResource(R.string.cancel)
                        )
                    }
                    TextButton(
                        onClick = {
                            onConfirm()
                        }
                    ) {
                        Text(
                            text = textBtnConfirm ?: stringResource(R.string.accept),
                            fontWeight = FontWeight.Bold
                        )
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
fun CustomConfirmDialogPreview() {
    MyAppTheme() {
        CustomConfirmDialog(
            modifier = Modifier,
            "TITULO",
            "Mensaje del cuadro de diálogo",
            "Actualizar ubicación",
            "Cancelar",
            {},
            {},
            {}
        )
    }
}

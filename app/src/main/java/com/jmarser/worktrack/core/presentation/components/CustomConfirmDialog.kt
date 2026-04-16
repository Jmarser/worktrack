package com.jmarser.worktrack.core.presentation.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jmarser.worktrack.R
import com.jmarser.worktrack.ui.theme.MyAppTheme
import com.jmarser.worktrack.ui.theme.appDimens

@Composable
fun CustomConfirmDialog(
    modifier: Modifier = Modifier,
    textTitle: String = stringResource(R.string.delete_header),
    textMessage: String,
    textBtnConfirm: String = stringResource(id = R.string.action_accept),
    textBtnCancel: String = stringResource(id = R.string.action_cancel),
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
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = appDimens.cardElevationLarge)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(appDimens.paddingLarge),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Surface(
                        modifier = Modifier
                            .size(appDimens.buttonHeightNormal),
                        shape = MaterialTheme.shapes.medium,
                        color = MaterialTheme.colorScheme.errorContainer.copy(alpha = .2f)
                    ) {
                        Icon(
                            imageVector = AppImages.ic_delete,
                            contentDescription = null,
                            modifier = Modifier.padding(appDimens.paddingMedium),
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                    HorizontalSpaceNormal()
                    Text(
                        text = textTitle,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                }

                VerticalSpaceLarge()

                Text(
                    text = textMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = appDimens.paddingSmall)
                )

                VerticalSpaceLarge()

                Surface(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(appDimens.paddingNormal),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(appDimens.paddingSmall)
                    ) {
                        Icon(
                            imageVector = AppImages.ic_info,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(appDimens.paddingMedium)
                                .size(appDimens.iconSizeNormal),
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Text(
                            modifier = Modifier
                                .weight(1f),
                            text = stringResource(R.string.delete_item_info_default),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 18.sp
                        )
                    }
                }

                VerticalSpaceExtraLarge()

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(appDimens.buttonHeightNormal),
                    onClick = {
                        onConfirm()
                    },
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    )
                ) {
                    Text(
                        text = textBtnConfirm,
                        fontWeight = FontWeight.Bold
                    )
                }

                VerticalSpaceSmall()

                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(appDimens.buttonHeightNormal),
                    onClick = {
                        onCancel()
                    }
                ) {
                    Text(
                        text = textBtnCancel,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
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
            stringResource(R.string.delete_header),
            "Mensaje del cuadro de diálogo",
            stringResource(R.string.action_delete),
            stringResource(R.string.action_cancel),
            {},
            {},
            {}
        )
    }
}

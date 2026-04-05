package com.jmarser.worktrack.core.presentation.components


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.worktrack.ui.theme.MyAppTheme
import com.jmarser.worktrack.ui.theme.appDimens

@Composable
fun <T> ButtonWithPb(
    modifier: Modifier = Modifier,
    label: String? = null,
    contentDescription: String? = null,
    semanticDescription: String? = null,
    iconStart: ImageVector? = null,
    iconEnd: ImageVector? = null,
    isEnabled: Boolean = true,
    displayProgressbar: Boolean = false,
    shape: RoundedCornerShape = RoundedCornerShape(appDimens.roundedShapePercent50),
    value: T,
    onClick: (T) -> Unit,
) {

    AnimatedContent(
        targetState = displayProgressbar,
        transitionSpec = {
            fadeIn(tween(250)) + scaleIn(tween(250)) togetherWith fadeOut(tween(250)) +
                    scaleOut(tween(250))
        },
        label = "AnimatedButton",
    ) {isLoading ->
        if (isLoading){
            CircularProgressIndicator(
                modifier = modifier
                        .size(appDimens.buttonHeightNormal)
                        .padding(appDimens.paddingMedium),
                strokeWidth = appDimens.borderNormal,
            )
        }else{
            Button(
                modifier = modifier
                    .height(appDimens.buttonHeightNormal)
                    .padding(horizontal = appDimens.paddingMedium),
                shape = shape,
                onClick = { onClick(value) },
                enabled = isEnabled
            ) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    iconStart?.let { icon ->
                        Icon(
                            modifier = Modifier
                                .padding(appDimens.paddingSmall),
                            imageVector = icon,
                            contentDescription = contentDescription,
                        )
                    }
                    label?.let { text ->
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    iconEnd?.let { icon ->
                        Icon(
                            modifier = Modifier
                                .padding(appDimens.paddingSmall),
                            imageVector = icon,
                            contentDescription = contentDescription,
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
fun ButtonWithPbPreview() {
    MyAppTheme() {
        ButtonWithPb(
            modifier = Modifier,
            iconStart = AppImages.ic_refresh,
            iconEnd = AppImages.ic_refresh,
            label = "Botton",
            value = Unit,
            onClick = {},
        )
    }
}

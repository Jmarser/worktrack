package com.jmarser.worktrack.core.presentation.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmarser.worktrack.ui.theme.MyAppTheme
import com.jmarser.worktrack.ui.theme.appDimens

@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String? = null,
    textError: String? = null,
    trailingIcon: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    shape: RoundedCornerShape = RoundedCornerShape(appDimens.iconSizeExtraSmall),
    isError: Boolean = false,
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier,
    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = textError?.let { 0.dp } ?: appDimens.paddingMedium)
                .onFocusChanged{focusState ->
                    if (!focusState.isFocused){
                        keyboardController?.hide()
                    }
                },
            value= value,
            onValueChange = onValueChange,
            placeholder = placeholderText?.let {
                {Text(it)}
            },
            trailingIcon = trailingIcon?.let {
                {Text(it)}
            },
            shape = shape,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = imeAction,
                showKeyboardOnFocus = true
            ),
            keyboardActions = KeyboardActions(
                onAny = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            ),
            isError = isError
        )

        textError?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = appDimens.paddingMedium),
                text = it,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }

    }

}

@Preview(
    showSystemUi = false,
    showBackground = true
)
@Composable
fun TextInputFieldPreview() {
    MyAppTheme() {
        TextInputField(
            modifier = Modifier,
            value = "",
            onValueChange = {},
            placeholderText = "Nombre",
            trailingIcon = "€",
            isError = false,
            textError = "Entrada no válida"
        )
    }
}

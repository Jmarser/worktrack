package com.jmarser.worktrack.presentation.createCompany.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jmarser.worktrack.R
import com.jmarser.worktrack.core.presentation.components.AppBar
import com.jmarser.worktrack.core.presentation.components.AppImages
import com.jmarser.worktrack.core.presentation.components.ButtonWithPb
import com.jmarser.worktrack.core.presentation.components.HorizontalSpaceMedium
import com.jmarser.worktrack.core.presentation.components.TextInputField
import com.jmarser.worktrack.core.presentation.components.VerticalSpaceLarge
import com.jmarser.worktrack.core.presentation.components.VerticalSpaceNormal
import com.jmarser.worktrack.domain.model.CurrencyType
import com.jmarser.worktrack.presentation.error.asString
import com.jmarser.worktrack.ui.theme.MyAppTheme
import com.jmarser.worktrack.ui.theme.appDimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCompanyScreen(
    modifier: Modifier = Modifier,
    companyId: Long = -1L,
    viewModel: CreateCompanyViewModel = hiltViewModel<CreateCompanyViewModel, CreateCompanyViewModel.Factory>(
        key = companyId.toString()
    ){factory ->
        factory.create(companyId)
    },
    navigateToBack: () -> Unit
) {

    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
    val formState by viewModel.formState.collectAsStateWithLifecycle()

    var expanded by remember { mutableStateOf(false) }
    val currencies = CurrencyType.entries
    val selectedCurrency = remember(formState.currencyType) {formState.currencyType }
    var pendingMessageResId by remember{mutableStateOf(0)}
    val isEdit = companyId != -1L

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when(effect){
                CreateCompanyEffect.NavigateToBack -> {
                    navigateToBack()
                }
                CreateCompanyEffect.NavigateToSettings -> {}
                is CreateCompanyEffect.ShowMessage -> {
                    pendingMessageResId = effect.resId
                }
            }
        }
    }

    val messageToShow = if (pendingMessageResId != 0) stringResource(pendingMessageResId) else ""
    LaunchedEffect(pendingMessageResId) {
        if (pendingMessageResId != 0){
            snackbarHostState.showSnackbar(messageToShow)
            pendingMessageResId = 0
        }
    }

    Scaffold(
        topBar = {
            AppBar(
                modifier = Modifier,
                title = stringResource(if (isEdit) R.string.update_company else R.string.createCompany),
                showOnBack = true,
                showFilters = false,
                showSettings = true,
                showAddIcon = false,
                onBackSelected = {
                    viewModel.onEvent(CreateCompanyEvent.onBackSelected)
                }
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {paddingValues ->

        val contentModifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()

        Column(
            modifier = contentModifier
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(appDimens.paddingMedium)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(appDimens.paddingMedium),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = AppImages.ic_company,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(appDimens.iconSizeExtraSmall)
                        )

                        HorizontalSpaceMedium()

                        Text(
                            text = stringResource(R.string.detailsCompany),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    VerticalSpaceLarge()

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = appDimens.paddingMedium),
                        text = stringResource(R.string.nameCompany),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    TextInputField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = formState.companyName,
                        onValueChange = {
                            viewModel.onEvent(CreateCompanyEvent.SetCompanyName(it))
                        },
                        isError = (formState.companyNameIsValid == false),
                        textError = formState.companyNameErrorMessage?.asString(),
                    )

                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(appDimens.paddingMedium)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(appDimens.paddingMedium),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = AppImages.ic_money,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(appDimens.iconSizeExtraSmall)
                        )

                        HorizontalSpaceMedium()

                        Text(
                            text = stringResource(R.string.fess),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    VerticalSpaceLarge()

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = appDimens.paddingMedium),
                        text = stringResource(R.string.payment_currency),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = selectedCurrency.toString(),
                            onValueChange = {},
                            readOnly = true, // Evita que el usuario escriba
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                            shape = MaterialTheme.shapes.medium
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            shape = MaterialTheme.shapes.medium
                        ) {
                            currencies.forEach { currency ->
                                DropdownMenuItem(
                                    text = { Text(currency.toString()) },
                                    onClick = {
                                        viewModel.onEvent(CreateCompanyEvent.SetCurrencyType(currency))
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    VerticalSpaceLarge()

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = appDimens.paddingMedium),
                        text = stringResource(R.string.full_day),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    TextInputField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = formState.fullTimePrice,
                        onValueChange = {
                            viewModel.onEvent(CreateCompanyEvent.SetFullTimePrice(it))
                        },
                        isError = (formState.fullTimePriceIsValid == false),
                        textError = formState.fullTimePriceErrorMessage?.asString(),
                        placeholderText = "0.00",
                        trailingIcon = selectedCurrency.symbol,
                        keyboardType = KeyboardType.Decimal
                    )

                    VerticalSpaceLarge()

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = appDimens.paddingMedium),
                        text = stringResource(R.string.half_day),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    TextInputField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = formState.halfTimePrice,
                        onValueChange = {
                            viewModel.onEvent(CreateCompanyEvent.SetHalfTimePrice(it))
                        },
                        isError = (formState.halfTimePriceIsValid == false),
                        textError = formState.halfTimePriceErrorMessage?.asString(),
                        placeholderText = "0.00",
                        trailingIcon = selectedCurrency.symbol,
                        keyboardType = KeyboardType.Decimal
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(appDimens.paddingMedium)
            ) {
                Row(
                    modifier = Modifier
                        .padding(appDimens.paddingSmall),
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        modifier = Modifier
                            .padding(appDimens.paddingMedium),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(appDimens.iconSizeLarge)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(appDimens.paddingMedium)
                    ) {
                        Text(
                            text = stringResource(R.string.billing_advice),
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stringResource(R.string.message_billing_advide),
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
                }
            }

            VerticalSpaceNormal()

            ButtonWithPb(
                modifier = Modifier
                    .padding(appDimens.iconSizeLarge)
                    .fillMaxWidth(),
                label = stringResource(if (isEdit) R.string.save_changes else R.string.save_company),
                iconStart = AppImages.ic_save,
                contentDescription = "guardar empresa",
                isEnabled = formState.isButtonEnabled,
                value = Unit,
                onClick = {
                    viewModel.onEvent(CreateCompanyEvent.onRegisterCompany)
                },
                displayProgressbar = formState.isLoading
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun CreateCompanyScreenPreview() {
    MyAppTheme() {
        CreateCompanyScreen(
            modifier = Modifier,
            companyId = -1L,
            navigateToBack = {}
        )
    }
}

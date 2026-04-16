package com.jmarser.worktrack.presentation.error

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jmarser.worktrack.R
import com.jmarser.worktrack.core.error.ValidationError

/**
 * Project: WorkTrack
 * File: ValidationErrorHelper.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 04/04/2026
 */
@Composable
fun ValidationError.asString(): String{
    return when(this){
        ValidationError.ErrorUnknown -> stringResource(R.string.error_unknown)

        ValidationError.EmptyField -> stringResource(R.string.error_empty_field)
        ValidationError.InvalidFormat -> stringResource(R.string.invalid_format_error)
        ValidationError.FullPriceRelationError -> stringResource(R.string.error_company_full_price_relation)
        ValidationError.HalfPriceRelationError -> stringResource(R.string.error_company_half_price_relation)

        ValidationError.NameCompanyRequired -> stringResource(R.string.create_company_name_required)
        ValidationError.CompanyAlreadyExists -> stringResource(R.string.error_company_already_exists)
        ValidationError.CompanyNotFound -> stringResource(R.string.company_not_found)
    }
}
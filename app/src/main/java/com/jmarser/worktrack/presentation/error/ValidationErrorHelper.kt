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

        ValidationError.EmptyField -> stringResource(R.string.empty_field_error)
        ValidationError.InvalidFormat -> stringResource(R.string.invalid_format_error)
        ValidationError.FullPriceRelationError -> stringResource(R.string.full_price_relation_error)
        ValidationError.HalfPriceRelationError -> stringResource(R.string.half_price_relation_error)

        ValidationError.NameCompanyRequired -> stringResource(R.string.name_company_required)
        ValidationError.CompanyAlreadyExists -> stringResource(R.string.company_already_exists)
    }
}
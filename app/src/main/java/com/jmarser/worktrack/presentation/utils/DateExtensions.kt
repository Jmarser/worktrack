package com.jmarser.worktrack.presentation.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Project: WorkTrack
 * File: DateExtensions.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 11/04/2026
 */

fun Date.toMonthAbbr(): String{
    val sdf = SimpleDateFormat("MMM", Locale.getDefault())
    return sdf.format(this).uppercase()
}

fun Date.toDayString(): String{
    val sdf = SimpleDateFormat("dd", Locale.getDefault())
    return sdf.format(this)
}

fun Date.toFullMonthYear(): String{
    val sdf = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
    val dateString = sdf.format(this)
    return dateString.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else  it.toString()}
}
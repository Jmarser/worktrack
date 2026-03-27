package com.jmarser.worktrack.data.local.database.util

import androidx.room.TypeConverter
import java.util.Date

/**
 * Project: WorkTrack
 * File: Converters.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

/**
 * Convertidor para las fechas, para pasar de long a date y de date a long
 * */
class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date?{
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long?{
        return date?.time
    }
}
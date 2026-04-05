package com.jmarser.worktrack.domain.model

/**
 * Project: WorkTrack
 * File: CurrencyType.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/04/2026
 */

enum class CurrencyType(name: String, val symbol: String) {
    EURO("Euro", "€"),
    LIBRA("Libra", "£"),
    DOLAR("Dólar", "$");

    override fun toString(): String = name.lowercase().replaceFirstChar { it.uppercase() }
}
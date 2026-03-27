package com.jmarser.worktrack.core.domain.coroutines

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Project: WorkTrack
 * File: DispatchersProvider.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

interface DispatchersProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}
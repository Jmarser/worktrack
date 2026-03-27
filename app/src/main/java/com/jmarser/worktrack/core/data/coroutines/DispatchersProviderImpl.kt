package com.jmarser.worktrack.core.data.coroutines

import com.jmarser.worktrack.core.domain.coroutines.DispatchersProvider
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Project: WorkTrack
 * File: DispatchersProviderImpl.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/03/2026
 */

class DispatchersProviderImpl @Inject constructor(): DispatchersProvider {
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val io: CoroutineDispatcher = Dispatchers.IO
    override val default: CoroutineDispatcher = Dispatchers.Default
    override val unconfined: CoroutineDispatcher = Dispatchers.Unconfined
}
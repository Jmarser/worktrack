package com.jmarser.worktrack.core.data.repository

import com.jmarser.worktrack.core.domain.coroutines.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

/**
 * Project: WorkTrack
 * File: BaseRepository.kt
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/03/2026
 */

abstract class BaseRepository(
    private val dispatchers: DispatchersProvider
) {
    /**
     * Ejecuta una acción suspendida en el hilo de IO.
     * Esto nos evita escribir 'withContext(dispatchers.io)' en cada método.
     */
    suspend fun <T> doWork(action: suspend () -> T): T {
        return withContext(dispatchers.io) {
            action()
        }
    }

    /**
     * Configura un Flow para que se ejecute en el hilo de IO.
     */
    fun <T> Flow<T>.asIoFlow(): Flow<T> {
        return this.flowOn(dispatchers.io)
    }
}
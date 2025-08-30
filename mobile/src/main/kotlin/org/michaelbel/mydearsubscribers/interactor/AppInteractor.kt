package org.michaelbel.mydearsubscribers.interactor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.michaelbel.core.dispatchers.AppDispatchers
import org.michaelbel.mydearsubscribers.repository.AppRepository
import org.michaelbel.mydearsubscribers.room.AppEntity

class AppInteractor(
    private val appDispatchers: AppDispatchers,
    private val appRepository: AppRepository
) {
    fun entitiesFlow(): Flow<List<AppEntity>> {
        return appRepository.entitiesFlow()
    }

    fun entityFlow(id: Int): Flow<AppEntity> {
        return appRepository.entityFlow(id)
    }

    suspend fun loadDataResponse() {
        withContext(appDispatchers.io) { appRepository.loadDataResponse() }
    }
}
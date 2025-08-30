package org.michaelbel.mydearsubscribers.interactor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.michaelbel.mydearsubscribers.dispatchers.AppDispatchers
import org.michaelbel.mydearsubscribers.repository.AppRepository
import org.michaelbel.mydearsubscribers.room.FollowersEntity

class AppInteractor(
    private val appDispatchers: AppDispatchers,
    private val appRepository: AppRepository
) {
    val entitiesFlow: Flow<List<FollowersEntity>> = appRepository.entitiesFlow

    suspend fun loadDataResponse() {
        withContext(appDispatchers.io) { appRepository.loadDataResponse() }
    }
}
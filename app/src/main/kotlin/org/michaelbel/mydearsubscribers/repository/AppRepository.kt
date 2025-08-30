package org.michaelbel.mydearsubscribers.repository

import kotlinx.coroutines.flow.Flow
import org.michaelbel.mydearsubscribers.ktor.AppService
import org.michaelbel.mydearsubscribers.room.AppDao
import org.michaelbel.mydearsubscribers.room.FollowersEntity

class AppRepository(
    private val appDao: AppDao,
    private val appService: AppService
) {
    val entitiesFlow: Flow<List<FollowersEntity>> = appDao.entitiesFlow()

    suspend fun loadDataResponse() {
        val appEntities = appService.getDataResponse().map {
            FollowersEntity(
                type = it.type,
                login = it.login,
                followers = it.followers,
                updatedAt = it.updatedAt
            )
        }
        appDao.upsertEntities(appEntities)
    }
}
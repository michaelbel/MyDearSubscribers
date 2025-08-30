package org.michaelbel.mydearsubscribers.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.michaelbel.mydearsubscribers.ktor.NetworkClient
import org.michaelbel.mydearsubscribers.room.Dao
import org.michaelbel.mydearsubscribers.room.FollowersEntity

class Repository(
    private val dao: Dao,
    private val networkClient: NetworkClient
) {
    val entitiesFlow: Flow<List<FollowersEntity>> = dao.entitiesFlow()

    suspend fun loadDataResponse() {
        withContext(Dispatchers.IO) {
            val appEntities = networkClient.dataResponse().map {
                FollowersEntity(
                    type = it.type,
                    login = it.login,
                    followers = it.followers,
                    updatedAt = it.updatedAt
                )
            }
            dao.upsertEntities(appEntities)
        }
    }
}
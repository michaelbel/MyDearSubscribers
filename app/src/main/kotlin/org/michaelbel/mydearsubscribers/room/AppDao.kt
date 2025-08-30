package org.michaelbel.mydearsubscribers.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM followers")
    fun entitiesFlow(): Flow<List<FollowersEntity>>

    @Query("SELECT * FROM followers WHERE type = :type")
    fun entityFlow(type: String): Flow<FollowersEntity>

    @Upsert
    suspend fun upsertEntities(entities: List<FollowersEntity>)
}
package org.michaelbel.mydearsubscribers.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Query("SELECT * FROM followers")
    fun entitiesFlow(): Flow<List<FollowersEntity>>

    @Upsert
    suspend fun upsertEntities(entities: List<FollowersEntity>)
}
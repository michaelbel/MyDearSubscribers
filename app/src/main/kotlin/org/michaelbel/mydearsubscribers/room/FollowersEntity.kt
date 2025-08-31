package org.michaelbel.mydearsubscribers.room

import androidx.room.Entity

@Entity(tableName = "followers", primaryKeys = ["type"])
data class FollowersEntity(
    val type: String,
    val followers: Int
) {
    companion object Companion {
        val Empty = FollowersEntity(type = "", followers = 0)
    }
}
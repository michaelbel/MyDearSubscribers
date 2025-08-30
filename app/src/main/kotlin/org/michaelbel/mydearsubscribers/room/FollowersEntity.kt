package org.michaelbel.mydearsubscribers.room

import androidx.room.Entity

@Entity(tableName = "followers", primaryKeys = ["type"])
data class FollowersEntity(
    val type: String,
    val login: String,
    val followers: Int,
    val updatedAt: String
) {
    companion object Companion {
        val Empty = FollowersEntity(type = "", login = "", followers = 0, updatedAt = "")
    }
}
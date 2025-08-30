package org.michaelbel.mydearsubscribers.ktor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FollowersResponse(
    @SerialName("type") val type: String,
    @SerialName("login") val login: String,
    @SerialName("followers") val followers: Int,
    @SerialName("updatedAt") val updatedAt: String
)
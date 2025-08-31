package org.michaelbel.mydearsubscribers.ktor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubscribersResponse(
    @SerialName("type") val type: String,
    @SerialName("followers") val followers: Int
)
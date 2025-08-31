package org.michaelbel.mydearsubscribers.ktor

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class NetworkClient(
    private val httpClient: HttpClient
) {
    suspend fun dataResponse(): List<SubscribersResponse> {
        val response = httpClient.get(DATA_URL).bodyAsText()
        return Json.decodeFromString<List<SubscribersResponse>>(response)
    }

    private companion object Companion {
        const val DATA_URL = "https://raw.githubusercontent.com/michaelbel/MyDearSubscribers/refs/heads/develop/.github/followers.json"
    }
}
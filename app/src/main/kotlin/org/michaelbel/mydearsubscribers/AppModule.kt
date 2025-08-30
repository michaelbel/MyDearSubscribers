package org.michaelbel.mydearsubscribers

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.michaelbel.mydearsubscribers.ktor.NetworkClient
import org.michaelbel.mydearsubscribers.repository.Repository
import org.michaelbel.mydearsubscribers.room.Dao
import org.michaelbel.mydearsubscribers.room.AppDatabase
import org.michaelbel.mydearsubscribers.ui.MainViewModel

val appModule = module {
    single<Dao> {
        val appDatabase = AppDatabase.getInstance(androidContext())
        appDatabase.appDao()
    }
    single<NetworkClient> {
        val ktorHttpClient = HttpClient(OkHttp) {
            defaultRequest {
                contentType(ContentType.Application.Json)
            }
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
        NetworkClient(ktorHttpClient)
    }
    single<Repository> { Repository(get(), get()) }
    viewModelOf(::MainViewModel)
}
package org.michaelbel.mydearsubscribers

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.michaelbel.mydearsubscribers.dispatchers.di.dispatchersKoinModule
import org.michaelbel.mydearsubscribers.interactor.AppInteractor
import org.michaelbel.mydearsubscribers.ktor.AppService
import org.michaelbel.mydearsubscribers.repository.AppRepository
import org.michaelbel.mydearsubscribers.room.AppDao
import org.michaelbel.mydearsubscribers.room.AppDatabase
import org.michaelbel.mydearsubscribers.ui.ListViewModel

val appModule = module {
    includes(dispatchersKoinModule)
    single<AppDao> {
        val appDatabase = AppDatabase.getInstance(androidContext())
        appDatabase.appDao()
    }
    single<AppService> {
        val chuckerInterceptor = ChuckerInterceptor.Builder(androidContext())
            .collector(
                collector = ChuckerCollector(
                    context = androidContext(),
                    showNotification = true,
                    retentionPeriod = RetentionManager.Period.ONE_HOUR
                )
            )
            .maxContentLength(
                length = 250_000L
            )
            .alwaysReadResponseBody(true)
            .build()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val ktorHttpClient = HttpClient(OkHttp) {
            defaultRequest {
                contentType(ContentType.Application.Json)
                url("https://api.example.com/")
            }
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = AppService.REQUEST_TIMEOUT_MILLIS
                connectTimeoutMillis = AppService.CONNECT_TIMEOUT_MILLIS
                socketTimeoutMillis = AppService.SOCKET_TIMEOUT_SECONDS
            }
            engine {
                clientCacheSize = AppService.HTTP_CACHE_SIZE_BYTES
                config {
                    addInterceptor(chuckerInterceptor)
                    addInterceptor(httpLoggingInterceptor)
                }
            }
        }
        AppService(ktorHttpClient)
    }
    single<AppRepository> { AppRepository(get(), get()) }
    single<AppInteractor> { AppInteractor(get(), get()) }
    viewModelOf(::ListViewModel)
}
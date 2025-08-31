package org.michaelbel.mydearsubscribers

import android.app.Application
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
        FirebaseMessaging.getInstance().subscribeToTopic("followers_updates")
    }
}
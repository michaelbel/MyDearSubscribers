package org.michaelbel.mydearsubscribers

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri
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
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val uri = Uri.parse("android.resource://$packageName/${R.raw.new_github_subscriber}")
        val attrs = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        val ch = NotificationChannel(
            "followers2",
            "Followers updates",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply { setSound(uri, attrs) }
        nm.createNotificationChannel(ch)
    }
}
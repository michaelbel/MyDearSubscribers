package org.michaelbel.mydearsubscribers.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FollowersMessagingService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("2", "onNewToken $token")
    }

    override fun onMessageReceived(msg: RemoteMessage) {
        val title = msg.notification?.title ?: "GitHub followers"
        val body = msg.notification?.body ?: "updated"
        val delta = msg.data["delta"] ?: "0"
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val n = NotificationCompat.Builder(this, "followers2")
            .setSmallIcon(android.R.drawable.stat_notify_more)
            .setContentTitle(title)
            .setContentText("$body (Δ $delta)")
            .setAutoCancel(true)
            .build()
        nm.notify(1001, n)
    }
}
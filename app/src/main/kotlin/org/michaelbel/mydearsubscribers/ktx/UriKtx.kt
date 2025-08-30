@file:Suppress("unused", "UnusedReceiverParameter")

package org.michaelbel.mydearsubscribers.ktx

import android.net.Uri

fun Uri.orEmpty(): Uri {
    return Uri.EMPTY
}
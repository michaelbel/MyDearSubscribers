package org.michaelbel.palettecolors

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute : NavKey {

    @Serializable
    data object List : AppRoute

    @Serializable
    data class Details(val boarId: Int) : AppRoute
}

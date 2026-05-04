package org.michaelbel.palettecolors

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import org.michaelbel.palettecolors.screen.DetailsScreen
import org.michaelbel.palettecolors.screen.ListScreen

@Composable
fun MainActivityContent() {
    var selectedBoarId by rememberSaveable { mutableStateOf<Int?>(null) }

    if (selectedBoarId == null) {
        ListScreen(
            onNavigateToDetails = { selectedBoarId = it }
        )
    } else {
        BackHandler { selectedBoarId = null }
        DetailsScreen(
            boarId = selectedBoarId!!,
            onBack = { selectedBoarId = null }
        )
    }
}

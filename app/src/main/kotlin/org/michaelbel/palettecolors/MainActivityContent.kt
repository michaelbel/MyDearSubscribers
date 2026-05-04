package org.michaelbel.palettecolors

import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import org.michaelbel.palettecolors.screen.DetailsScreen
import org.michaelbel.palettecolors.screen.ListScreen

@Composable
fun MainActivityContent() {
    val backStack = rememberNavBackStack(AppRoute.List)

    NavDisplay(
        backStack = backStack,
        modifier = Modifier.fillMaxSize(),
        popTransitionSpec = { fadeIn() togetherWith fadeOut() using SizeTransform(clip = false) },
        predictivePopTransitionSpec = { fadeIn() togetherWith fadeOut() using SizeTransform(clip = false) },
        entryDecorators = listOf(rememberSaveableStateHolderNavEntryDecorator()),
        entryProvider = entryProvider {
            entry<AppRoute.List> {
                ListScreen(
                    onNavigateToDetails = { boarId -> backStack.add(AppRoute.Details(boarId)) }
                )
            }
            entry<AppRoute.Details> { route ->
                DetailsScreen(
                    boarId = route.boarId,
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        }
    )
}

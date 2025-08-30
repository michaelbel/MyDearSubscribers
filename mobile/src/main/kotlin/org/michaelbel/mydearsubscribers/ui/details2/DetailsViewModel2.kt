@file:OptIn(ExperimentalCoroutinesApi::class)

package org.michaelbel.mydearsubscribers.ui.details2

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import org.michaelbel.core.viewmodel.BaseViewModel
import org.michaelbel.mydearsubscribers.interactor.AppInteractor
import org.michaelbel.mydearsubscribers.room.AppEntity

class DetailsViewModel2(
    appInteractor: AppInteractor
): BaseViewModel() {

    var idFlow = MutableStateFlow(0)

    val appEntity: StateFlow<AppEntity> = idFlow.flatMapLatest {
        appInteractor.entityFlow(it)
    }.stateIn(
        scope = this,
        started = SharingStarted.Lazily,
        initialValue = AppEntity.Empty
    )
}
package org.michaelbel.mydearsubscribers.ui

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.michaelbel.mydearsubscribers.interactor.AppInteractor
import org.michaelbel.mydearsubscribers.room.FollowersEntity
import org.michaelbel.mydearsubscribers.viewmodel.BaseViewModel

class ListViewModel(
    private val appInteractor: AppInteractor
): BaseViewModel() {

    val appEntities: StateFlow<List<FollowersEntity>> = appInteractor.entitiesFlow
        .stateIn(
            scope = this,
            started = SharingStarted.Companion.Lazily,
            initialValue = emptyList()
        )

    init {
        launch { appInteractor.loadDataResponse() }
    }
}
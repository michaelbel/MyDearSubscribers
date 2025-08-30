package org.michaelbel.mydearsubscribers.ui

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.michaelbel.mydearsubscribers.repository.Repository
import org.michaelbel.mydearsubscribers.room.FollowersEntity
import org.michaelbel.mydearsubscribers.viewmodel.BaseViewModel

class MainViewModel(
    private val repository: Repository
): BaseViewModel() {

    val entities: StateFlow<List<FollowersEntity>> = repository.entitiesFlow
        .stateIn(
            scope = this,
            started = SharingStarted.Companion.Lazily,
            initialValue = emptyList()
        )

    init {
        launch { repository.loadDataResponse() }
    }
}
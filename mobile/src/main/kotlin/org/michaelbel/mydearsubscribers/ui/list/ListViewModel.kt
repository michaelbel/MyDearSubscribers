package org.michaelbel.mydearsubscribers.ui.list

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.michaelbel.core.viewmodel.BaseViewModel
import org.michaelbel.mydearsubscribers.interactor.AppInteractor
import org.michaelbel.mydearsubscribers.room.AppEntity

class ListViewModel(
    private val appInteractor: AppInteractor
): BaseViewModel() {

    val appEntities: StateFlow<List<AppEntity>> = appInteractor.entitiesFlow()
        .stateIn(
            scope = this,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    init {
        launch { appInteractor.loadDataResponse() }
    }
}
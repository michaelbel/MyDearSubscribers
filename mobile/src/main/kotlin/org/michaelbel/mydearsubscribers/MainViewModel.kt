package org.michaelbel.mydearsubscribers

import org.michaelbel.core.viewmodel.BaseViewModel
import org.michaelbel.mydearsubscribers.interactor.AppInteractor

class MainViewModel(
    private val appInteractor: AppInteractor
): BaseViewModel()
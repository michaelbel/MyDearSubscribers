package org.michaelbel.mydearsubscribers.dispatchers.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.michaelbel.mydearsubscribers.dispatchers.AppDispatchers
import org.michaelbel.mydearsubscribers.dispatchers.impl.AppDispatchersImpl

val dispatchersKoinModule = module {
    singleOf(::AppDispatchersImpl) { bind<AppDispatchers>() }
}
package com.wiseowl.splitride.core.di

import com.wiseowl.splitride.core.storage.AuthenticationStorage
import com.wiseowl.splitride.core.storage.StorageManager
import com.wiseowl.splitride.core.storage.UserDetailStorage
import com.wiseowl.splitride.core.ui.routing.EventBus
import com.wiseowl.splitride.core.ui.routing.EventBusImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val sharedModule = module {
    single { StorageManager(get()) }
    single { AuthenticationStorage(get()) }
    single { UserDetailStorage(get()) }
    single<EventBus> { EventBusImpl() }
}

expect val platformModule: Module
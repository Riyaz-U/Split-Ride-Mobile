package com.wiseowl.splitride.core.di

import com.wiseowl.splitride.core.storage.AuthenticationStorage
import com.wiseowl.splitride.core.storage.StorageManager
import org.koin.core.module.Module
import org.koin.dsl.module

val sharedModule = module {
    single { StorageManager(get()) }
    single { AuthenticationStorage(get()) }
}

expect val platformModule: Module
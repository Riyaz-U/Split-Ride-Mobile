package com.wiseowl.splitride.core.di

import com.wiseowl.splitride.core.storage.getVaultFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { getVaultFactory() }
}
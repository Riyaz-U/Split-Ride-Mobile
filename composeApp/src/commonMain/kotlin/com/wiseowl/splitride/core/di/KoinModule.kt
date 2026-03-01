package com.wiseowl.splitride.core.di

import com.wiseowl.splitride.authentication.data.AuthenticationRepositoryImpl
import com.wiseowl.splitride.authentication.domain.AuthenticationService
import com.wiseowl.splitride.authentication.domain.usecase.InputValidationUseCase
import com.wiseowl.splitride.authentication.domain.usecase.RegistrationUseCase
import com.wiseowl.splitride.authentication.presentation.registration.RegistrationViewModel
import com.wiseowl.splitride.core.network.ApiService
import com.wiseowl.splitride.core.network.interceptor.AuthInterceptor
import com.wiseowl.splitride.core.network.interceptor.Interceptor
import com.wiseowl.splitride.core.storage.AuthenticationStorage
import com.wiseowl.splitride.core.storage.StorageManager
import com.wiseowl.splitride.core.storage.UserDetailStorage
import com.wiseowl.splitride.core.ui.routing.EventBus
import com.wiseowl.splitride.core.ui.routing.EventBusImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val sharedModule = module {
    single { StorageManager(get()) }
    single { AuthenticationStorage(get()) }
    single { UserDetailStorage(get()) }
    single<EventBus> { EventBusImpl() }
    single<Interceptor> { AuthInterceptor(get()) }
    single { ApiService() }
    single { InputValidationUseCase() }
    single<AuthenticationService> { AuthenticationRepositoryImpl(get(), get()) }
    single { RegistrationUseCase(get()) }

    //ViewModels
    viewModel { RegistrationViewModel(get(), get(), get()) }
}

expect val platformModule: Module
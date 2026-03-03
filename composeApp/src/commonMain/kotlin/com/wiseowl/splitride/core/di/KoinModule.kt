package com.wiseowl.splitride.core.di

import com.wiseowl.splitride.authentication.data.AuthenticationRepositoryImpl
import com.wiseowl.splitride.authentication.domain.AuthenticationService
import com.wiseowl.splitride.authentication.domain.usecase.InputValidationUseCase
import com.wiseowl.splitride.authentication.domain.usecase.LoginUseCase
import com.wiseowl.splitride.authentication.domain.usecase.RegistrationUseCase
import com.wiseowl.splitride.authentication.presentation.login.LoginViewModel
import com.wiseowl.splitride.authentication.presentation.registration.RegistrationViewModel
import com.wiseowl.splitride.core.network.ApiService
import com.wiseowl.splitride.core.network.interceptor.AuthInterceptor
import com.wiseowl.splitride.core.network.interceptor.Interceptor
import com.wiseowl.splitride.core.storage.AuthenticationStorage
import com.wiseowl.splitride.core.storage.StorageManager
import com.wiseowl.splitride.core.storage.UserDetailStorage
import com.wiseowl.splitride.core.ui.routing.EventBus
import com.wiseowl.splitride.core.ui.routing.EventBusImpl
import com.wiseowl.splitride.ride.home.data.HomeRepositoryImpl
import com.wiseowl.splitride.ride.home.domain.HomeRepository
import com.wiseowl.splitride.ride.home.presentation.HomeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val sharedModule = module {
    single { StorageManager(get()) }
    single { AuthenticationStorage(get()) }
    single { UserDetailStorage(get()) }
    single<EventBus> { EventBusImpl() }
    single<Interceptor> { AuthInterceptor(get()) }
    single { ApiService(get()) }
    single { InputValidationUseCase() }
    single<AuthenticationService> { AuthenticationRepositoryImpl(get(), get()) }
    single { LoginUseCase(get()) }
    single { RegistrationUseCase(get()) }
    single<HomeRepository> { HomeRepositoryImpl(get(), get()) }

    //ViewModels
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { RegistrationViewModel(get(), get(), get()) }
    viewModel { HomeViewModel(get(), get()) }
}

expect val platformModule: Module
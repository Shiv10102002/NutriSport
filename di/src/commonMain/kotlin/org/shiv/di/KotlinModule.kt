package org.shiv.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.shiv.CustomerRepositoryImpl
import org.shiv.auth.AuthViewModel
import org.shiv.data.domain.CustomerRepository
import org.shiv.home.HomeGraphViewModel
import org.shiv.profile.ProfileViewModel


val sharedModule = module {
    single<CustomerRepository> { CustomerRepositoryImpl() }
    viewModelOf(::AuthViewModel)
    viewModelOf(::HomeGraphViewModel)
    viewModelOf(::ProfileViewModel)
}


fun initializeKoin(
    config:(KoinApplication.()->Unit)? = null
){
    startKoin {
        config?.invoke(this)
        modules(sharedModule)
    }
}
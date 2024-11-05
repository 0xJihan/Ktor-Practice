package com.jihan.ktor.di

import com.jihan.ktor.data.network.DrinkApi
import com.jihan.ktor.data.network.httpClient
import com.jihan.ktor.data.repository.DrinkRepository
import com.jihan.ktor.data.viewmodel.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {

    single { DrinkApi(get()) }

    single { DrinkRepository(get()) }

    single { httpClient }

    viewModel { MainViewModel(get())}


}
package com.jihan.ktor.di

import org.koin.dsl.module

    val appModule = module {
        includes(repositoryModule)
    }



package com.kingslayer06.clockapp.di

import com.kingslayer06.clockapp.database.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val targetModule = module {
    single { DatabaseDriverFactory(androidContext()) }
}
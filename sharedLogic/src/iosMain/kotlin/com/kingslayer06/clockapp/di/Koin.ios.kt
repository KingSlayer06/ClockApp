package com.kingslayer06.clockapp.di

import com.kingslayer06.clockapp.database.DatabaseDriverFactory
import org.koin.dsl.module

actual val targetModule = module {
    single { DatabaseDriverFactory() }
}
package com.kingslayer06.clockapp.di

import com.kingslayer06.clockapp.presentation.viewModels.ClockViewModel
import com.kingslayer06.clockapp.presentation.viewModels.SettingsViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}

val appModule = module {
    factory { SettingsViewModel() }
    factory { ClockViewModel() }
}

class ViewModelProvider: KoinComponent {
    fun provideClockViewModel(): ClockViewModel = get()
    fun provideSettingsViewModel(): SettingsViewModel = get()
}
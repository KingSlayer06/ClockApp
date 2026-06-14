package com.kingslayer06.clockapp.di

import com.kingslayer06.clockapp.data.repository.GameHistoryRepositoryImpl
import com.kingslayer06.clockapp.database.ClockDatabase
import com.kingslayer06.clockapp.database.DatabaseDriverFactory
import com.kingslayer06.clockapp.domain.repository.GameHistoryRepository
import com.kingslayer06.clockapp.domain.useCase.DeleteByIdUseCase
import com.kingslayer06.clockapp.domain.useCase.GetAllUseCase
import com.kingslayer06.clockapp.domain.useCase.GetByIdUseCase
import com.kingslayer06.clockapp.domain.useCase.InsertUseCase
import com.kingslayer06.clockapp.presentation.viewModels.ClockViewModel
import com.kingslayer06.clockapp.presentation.viewModels.GameHistoryViewModel
import com.kingslayer06.clockapp.presentation.viewModels.SettingsViewModel
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

expect val targetModule: Module

fun initKoin(
    config: (KoinApplication.() -> Unit)? = null
) {
    startKoin {
        config?.invoke(this)
        modules(targetModule, sharedModule)
    }
}

private val sharedModule = module {
    // Database
    single { ClockDatabase(get<DatabaseDriverFactory>().createDriver()) }

    // Repositories
    single<GameHistoryRepository> { GameHistoryRepositoryImpl(get()) }
    
    // Use Cases
    factory { InsertUseCase(get()) }
    factory { GetAllUseCase(get()) }
    factory { GetByIdUseCase(get()) }
    factory { DeleteByIdUseCase(get()) }
    
    // View Models
    factory { SettingsViewModel() }
    factory { ClockViewModel(get()) }
    factory { GameHistoryViewModel(get(), get()) }
}

class ViewModelProvider: KoinComponent {
    fun provideClockViewModel(): ClockViewModel = get()
    fun provideSettingsViewModel(): SettingsViewModel = get()
    fun provideGameHistoryViewModel(): GameHistoryViewModel = get()
}

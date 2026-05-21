package com.kingslayer06.clockapp.di

import com.kingslayer06.clockapp.data.repository.GameRepository
import com.kingslayer06.clockapp.domain.repository.IGameRepository
import com.kingslayer06.clockapp.domain.useCases.PauseGameUseCase
import com.kingslayer06.clockapp.domain.useCases.ResetGameUseCase
import com.kingslayer06.clockapp.domain.useCases.ResumeGameUseCase
import com.kingslayer06.clockapp.domain.useCases.StartGameUseCase
import com.kingslayer06.clockapp.domain.useCases.SwitchPlayerTurnUseCase
import com.kingslayer06.clockapp.presentation.viewModels.ClockViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        module {
            single<IGameRepository> { GameRepository() }

            single { StartGameUseCase(get()) }
            single { PauseGameUseCase(get()) }
            single { ResumeGameUseCase(get()) }
            single { SwitchPlayerTurnUseCase(get()) }
            single { ResetGameUseCase(get()) }

            factory {
                ClockViewModel(
                    get(),
                    get(),
                    get(),
                    get(),
                    get()
                )
            }
        }
    }
}

class ViewModelProvider: KoinComponent {
    fun provideClockViewModel(): ClockViewModel = get()
}
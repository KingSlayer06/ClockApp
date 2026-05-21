package com.kingslayer06.clockapp.domain.useCases

import com.kingslayer06.clockapp.domain.models.ClockUiState
import com.kingslayer06.clockapp.domain.models.GameState
import com.kingslayer06.clockapp.domain.repository.IGameRepository

class StartGameUseCase(
    private val gameRepository: IGameRepository
) {
    fun execute(currentState: ClockUiState) = gameRepository.startGame(currentState)
}
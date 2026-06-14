package com.kingslayer06.clockapp.domain.useCase

import com.kingslayer06.clockapp.domain.models.GameHistory
import com.kingslayer06.clockapp.domain.repository.GameHistoryRepository

class InsertUseCase(
    private val repository: GameHistoryRepository
) {
    suspend fun execute(game: GameHistory) = repository.insert(game)
}
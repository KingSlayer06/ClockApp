package com.kingslayer06.clockapp.domain.useCase

import com.kingslayer06.clockapp.domain.repository.GameHistoryRepository

class GetAllUseCase(
    private val repository: GameHistoryRepository
) {
    suspend fun execute() = repository.getAll()
}
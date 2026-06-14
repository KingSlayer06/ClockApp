package com.kingslayer06.clockapp.domain.useCase

import com.kingslayer06.clockapp.domain.repository.GameHistoryRepository

class GetByIdUseCase(
    private val repository: GameHistoryRepository
) {
    suspend fun execute(id: Long) = repository.getById(id)
}
package com.kingslayer06.clockapp.domain.useCases

import com.kingslayer06.clockapp.domain.models.ChessRuleset
import com.kingslayer06.clockapp.domain.repository.IGameRepository

class ResetGameUseCase(
    private val gameRepository: IGameRepository
) {
    fun execute(ruleset: ChessRuleset) = gameRepository.resetGame(ruleset)
}
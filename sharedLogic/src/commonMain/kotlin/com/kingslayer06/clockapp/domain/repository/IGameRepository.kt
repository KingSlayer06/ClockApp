package com.kingslayer06.clockapp.domain.repository

import com.kingslayer06.clockapp.domain.models.ActivePlayer
import com.kingslayer06.clockapp.domain.models.ChessRuleset
import com.kingslayer06.clockapp.domain.models.ClockUiState
import com.kingslayer06.clockapp.domain.models.GameState

interface IGameRepository {
    fun startGame(currentState: ClockUiState): ClockUiState
    fun pauseGame(currentState: ClockUiState): ClockUiState
    fun resumeGame(currentState: ClockUiState): ClockUiState
    fun resetGame(ruleset: ChessRuleset): ClockUiState
    fun switchPlayerTurn(currentState: ClockUiState, from: ActivePlayer): ClockUiState
}
package com.kingslayer06.clockapp.data.repository

import com.kingslayer06.clockapp.domain.models.ActivePlayer
import com.kingslayer06.clockapp.domain.models.ChessRuleset
import com.kingslayer06.clockapp.domain.models.ClockUiState
import com.kingslayer06.clockapp.domain.models.GameState
import com.kingslayer06.clockapp.domain.repository.IGameRepository

class GameRepository: IGameRepository {
    override fun startGame(currentState: ClockUiState): ClockUiState {
        // If game is finished, return
        if (currentState.gameState == GameState.FINISHED) return currentState

        // If active player is not set, set it to player one
        val nextPlayer = if (currentState.activePlayer == ActivePlayer.NONE)
            ActivePlayer.PLAYER_ONE else currentState.activePlayer

        return currentState.copy(gameState = GameState.RUNNING, activePlayer = nextPlayer)
    }

    override fun pauseGame(currentState: ClockUiState): ClockUiState {
        // If game is not running, return
        if (currentState.gameState != GameState.RUNNING) return currentState

        return currentState.copy(gameState = GameState.PAUSED)
    }

    override fun resumeGame(currentState: ClockUiState): ClockUiState {
        // If game is not paused, return
        if (currentState.gameState != GameState.PAUSED) return currentState

        return currentState.copy(gameState = GameState.RUNNING)
    }

    override fun resetGame(ruleset: ChessRuleset): ClockUiState {
        return ClockUiState(
            playerOneTimeLeft = ruleset.initialTimeMillis,
            playerTwoTimeLeft = ruleset.initialTimeMillis,
            activePlayer = ActivePlayer.NONE,
            gameState = GameState.IDLE,
            currentRuleset = ruleset,
            winner = ActivePlayer.NONE
        )
    }

    override fun switchPlayerTurn(
        currentState: ClockUiState,
        from: ActivePlayer
    ): ClockUiState {
        // If game is not running or not player's, return
        if (currentState.gameState != GameState.RUNNING || currentState.activePlayer != from) {
            return currentState
        }

        var p1Time = currentState.playerOneTimeLeft
        var p2Time = currentState.playerTwoTimeLeft
        val increment = currentState.currentRuleset.incrementMillis

        val nextPlayer = if (from == ActivePlayer.PLAYER_ONE) {
            p1Time += increment
            ActivePlayer.PLAYER_TWO
        } else {
            p2Time += increment
            ActivePlayer.PLAYER_ONE
        }

        return currentState.copy(
            playerOneTimeLeft = p1Time,
            playerTwoTimeLeft = p2Time,
            activePlayer = nextPlayer
        )
    }
}
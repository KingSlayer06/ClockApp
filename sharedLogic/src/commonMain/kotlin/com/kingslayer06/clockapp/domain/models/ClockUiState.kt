package com.kingslayer06.clockapp.domain.models

enum class ActivePlayer { PLAYER_ONE, PLAYER_TWO, NONE }
enum class GameState { IDLE, RUNNING, PAUSED, FINISHED }

data class ClockUiState(
    val playerOneTimeLeft: Long = 0,
    val playerTwoTimeLeft: Long = 0,
    val activePlayer: ActivePlayer = ActivePlayer.NONE,
    val gameState: GameState = GameState.IDLE,
    val currentRuleset: ChessRuleset = ChessRuleset.Blitz,
    val winner: ActivePlayer = ActivePlayer.NONE
)
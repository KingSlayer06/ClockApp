package com.kingslayer06.clockapp.domain.models

enum class Player { ONE, TWO }
enum class GamePhase { IDLE, RUNNING, PAUSED, FINISHED }
enum class ClockTimeState { NORMAL, WARNING, DANGER, EXPIRED }

data class ClockUiState(
    val phase: GamePhase = GamePhase.IDLE,
    val activePlayer: Player? = null,
    val playerOneTimeMs: Long = 0L,
    val playerTwoTimeMs: Long = 0L,
    val playerOneMoves: Int = 0,
    val playerTwoMoves: Int = 0,
    val ruleset: ChessRuleset = ChessRuleset.Blitz,
    val winner: Player? = null,
    val playerOneTimeState: ClockTimeState = ClockTimeState.NORMAL,
    val playerTwoTimeState: ClockTimeState = ClockTimeState.NORMAL
)
package com.kingslayer06.clockapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kingslayer06.clockapp.domain.models.Player
import com.kingslayer06.clockapp.domain.models.ChessRuleset
import com.kingslayer06.clockapp.domain.models.ClockUiState
import com.kingslayer06.clockapp.domain.models.GamePhase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ClockViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(ClockUiState())
    val uiState = _uiState.asStateFlow()

    private var tickerJob: Job? = null

    init {
        selectRuleset(ChessRuleset.Blitz)
    }

    fun selectRuleset(ruleset: ChessRuleset) {
        stopTicker()
        val initialMs = ruleset.minutes * 60 * 1000L
        _uiState.update {
            ClockUiState(
                playerOneTimeMs = initialMs,
                playerTwoTimeMs = initialMs,
                ruleset = ruleset,
                phase = GamePhase.IDLE
            )
        }
    }

    fun startClock() {
        val state = _uiState.value
        if (state.phase != GamePhase.IDLE) return

        _uiState.update { it.copy(
            phase = GamePhase.RUNNING,
            activePlayer = Player.ONE
        )}
        startTicker()
    }

    fun pauseClock() {
        if (_uiState.value.phase != GamePhase.RUNNING) return

        stopTicker()
        _uiState.update { it.copy(phase = GamePhase.PAUSED) }
    }

    fun resumeClock() {
        if (_uiState.value.phase != GamePhase.PAUSED) return

        _uiState.update { it.copy(phase = GamePhase.RUNNING) }
        startTicker()
    }

    fun resetClock(ruleset: ChessRuleset) {
        selectRuleset(ruleset)
    }

    fun handlePlayerTap(player: Player) {
        val state = _uiState.value
        if (state.phase != GamePhase.RUNNING) return
        if (state.activePlayer != player) return

        _uiState.update { current ->
            val nextPlayer = if (player == Player.ONE) Player.TWO else Player.ONE

            val incrementMs = current.ruleset.increment * 1000L
            current.copy(
                activePlayer = nextPlayer,
                playerOneTimeMs = if (player == Player.ONE)
                    current.playerOneTimeMs + incrementMs
                    else current.playerOneTimeMs,

                playerTwoTimeMs = if (player == Player.TWO)
                    current.playerTwoTimeMs + incrementMs
                    else current.playerTwoTimeMs,

                playerOneMoves = if (player == Player.ONE)
                    current.playerOneMoves + 1
                    else current.playerOneMoves,

                playerTwoMoves = if (player == Player.TWO)
                    current.playerTwoMoves + 1
                    else current.playerTwoMoves
            )
        }
    }

    private fun startTicker() {
        if (tickerJob?.isActive == true) return

        tickerJob = viewModelScope.launch(Dispatchers.Default) {
            val tickInterval = 100L

            while (isActive) {
                delay(tickInterval)

                var gameFinished = false

                _uiState.update { current ->
                    // If game is not running, return
                    if (current.phase != GamePhase.RUNNING) return@update current

                    var p1Time = current.playerOneTimeMs
                    var p2Time = current.playerTwoTimeMs
                    var phase = current.phase
                    var winner = current.winner

                    when (current.activePlayer) {
                        Player.ONE -> {
                            p1Time = (p1Time - tickInterval).coerceAtLeast(0)

                            // Player one won
                            if (p1Time <= 0) {
                                winner = Player.TWO
                                phase = GamePhase.FINISHED
                            }
                        }
                        Player.TWO -> {
                            p2Time = (p2Time - 100).coerceAtLeast(0)

                            // Player two won
                            if (p2Time <= 0) {
                                winner = Player.ONE
                                phase = GamePhase.FINISHED
                            }
                        }
                        else -> {}
                    }

                    current.copy(
                        playerOneTimeMs = p1Time,
                        playerTwoTimeMs = p2Time,
                        phase = phase,
                        winner = winner
                    )
                }

                // If game is finished, stop ticker
                if (_uiState.value.phase == GamePhase.FINISHED) {
                    break
                }
            }
        }
    }

    private fun stopTicker() {
        tickerJob?.cancel()
        tickerJob = null
    }

    override fun onCleared() {
        super.onCleared()
        stopTicker()
    }
}
package com.kingslayer06.clockapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kingslayer06.clockapp.domain.models.ActivePlayer
import com.kingslayer06.clockapp.domain.models.ChessRuleset
import com.kingslayer06.clockapp.domain.models.ClockUiState
import com.kingslayer06.clockapp.domain.models.GameState
import com.kingslayer06.clockapp.domain.useCases.PauseGameUseCase
import com.kingslayer06.clockapp.domain.useCases.ResetGameUseCase
import com.kingslayer06.clockapp.domain.useCases.StartGameUseCase
import com.kingslayer06.clockapp.domain.useCases.SwitchPlayerTurnUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ClockViewModel(
    private val startGameUseCase: StartGameUseCase,
    private val pauseGameUseCase: PauseGameUseCase,
    private val resumeGameUseCase: StartGameUseCase,
    private val switchPlayerTurnUseCase: SwitchPlayerTurnUseCase,
    private val resetGameUseCase: ResetGameUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(ClockUiState())
    val uiState = _uiState.asStateFlow()

    private var tickerJob: Job? = null

    init {
        selectRuleset(ChessRuleset.Blitz)
    }

    fun selectRuleset(ruleset: ChessRuleset) {
        stopTicker()
        _uiState.value = resetGameUseCase.execute(ruleset)
    }

    fun startGame() {
        val currentState = _uiState.value
        _uiState.value = startGameUseCase.execute(currentState)
        startTicker()
    }

    fun pauseGame() {
        val currentState = _uiState.value
        stopTicker()
        _uiState.update { currentState ->
            pauseGameUseCase.execute(currentState)
        }
    }

    fun resumeGame() {
        val currentState = _uiState.value
        _uiState.update { currentState ->
            resumeGameUseCase.execute(currentState)
        }
        startTicker()
    }

    fun resetGame(ruleset: ChessRuleset) {
        stopTicker()
        _uiState.value = resetGameUseCase.execute(ruleset)
    }

    fun handlePlayerTap(player: ActivePlayer) {
        _uiState.update { currentState ->
            switchPlayerTurnUseCase.execute(currentState, player)
        }
    }

    private fun startTicker() {
        if (tickerJob?.isActive == true) return

        tickerJob = viewModelScope.launch(Dispatchers.Default) {
            while (isActive) {
                delay(100)
                _uiState.update { current ->
                    // If game is not running, return
                    if (current.gameState != GameState.RUNNING) return@update current

                    var p1Time = current.playerOneTimeLeft
                    var p2Time = current.playerTwoTimeLeft
                    var gameState = current.gameState
                    var winner = current.winner

                    when (current.activePlayer) {
                        ActivePlayer.PLAYER_ONE -> {
                            p1Time = (p1Time - 100).coerceAtLeast(0)

                            // Player one won
                            if (p1Time == 0L) {
                                winner = ActivePlayer.PLAYER_TWO
                                gameState = GameState.FINISHED
                            }
                        }
                        ActivePlayer.PLAYER_TWO -> {
                            p2Time = (p2Time - 100).coerceAtLeast(0)

                            // Player two won
                            if (p2Time == 0L) {
                                winner = ActivePlayer.PLAYER_ONE
                                gameState = GameState.FINISHED
                            }
                        }
                        else -> {}
                    }

                    current.copy(
                        playerOneTimeLeft = p1Time,
                        playerTwoTimeLeft = p2Time,
                        gameState = gameState,
                        winner = winner
                    )
                }

                // If game is finished, stop ticker
                if (_uiState.value.gameState == GameState.FINISHED) {
                    cancel()
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
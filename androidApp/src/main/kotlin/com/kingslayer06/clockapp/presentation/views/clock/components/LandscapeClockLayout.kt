package com.kingslayer06.clockapp.presentation.views.clock.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.kingslayer06.clockapp.core.ui.ColorBorder
import com.kingslayer06.clockapp.domain.models.ClockUiState
import com.kingslayer06.clockapp.domain.models.GamePhase
import com.kingslayer06.clockapp.domain.models.Player

@Composable
fun LandscapeClockLayout(
    state: ClockUiState,
    onPlayerOneTap: () -> Unit,
    onPlayerTwoTap: () -> Unit,
    onStart: () -> Unit,
    onPause: () -> Unit,
    onResume: () -> Unit,
    onReset: () -> Unit,
    onBack: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        RulesetBadge(ruleset = state.ruleset, modifier = Modifier.align(Alignment.CenterHorizontally))

        Row(modifier = Modifier.weight(1f)) {

            // Player 1 panel rotated 180 degrees to face the player
            PlayerPanel(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .rotate(180f),
                playerLabel = "Player 1",
                timeMillis = state.playerOneTimeMs,
                initialTimeMs = (state.ruleset.minutes * 60 * 1000L),
                moveCount = state.playerOneMoves,
                isActive = state.activePlayer == Player.ONE,
                isFinished = state.phase == GamePhase.FINISHED,
                onTap = onPlayerOneTap
            )

            // Divider
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(ColorBorder)
            )

            // Player 2 panel
            PlayerPanel(
                modifier    = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                playerLabel = "Player 2",
                timeMillis = state.playerTwoTimeMs,
                initialTimeMs = (state.ruleset.minutes * 60 * 1000L),
                moveCount = state.playerTwoMoves,
                isActive = state.activePlayer == Player.TWO,
                isFinished = state.phase == GamePhase.FINISHED,
                onTap = onPlayerTwoTap
            )
        }

        // Controls
        ControlsBar(
            phase  = state.phase,
            onStart = onStart,
            onPause = onPause,
            onResume = onResume,
            onReset = onReset,
            onBack = onBack
        )
    }
}
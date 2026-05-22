package com.kingslayer06.clockapp.presentation.views.clock.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.kingslayer06.clockapp.core.ui.ColorBorder
import com.kingslayer06.clockapp.core.ui.ColorSurface
import com.kingslayer06.clockapp.domain.models.ClockUiState
import com.kingslayer06.clockapp.domain.models.GamePhase
import com.kingslayer06.clockapp.domain.models.Player

@Composable
fun PortraitClockLayout(
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
        // Player 1 panel rotated 180 degrees to face the player
        PlayerPanel(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .rotate(180f),
            playerLabel = "Player 1",
            timeMillis = state.playerOneTimeMs,
            initialTimeMs = (state.ruleset.minutes * 60 * 1000L),
            moveCount = state.playerOneMoves,
            isActive = state.activePlayer == Player.ONE,
            isFinished = state.phase == GamePhase.FINISHED,
            onTap = onPlayerOneTap
        )

        // Controls
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorSurface),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(ColorBorder))

            Row(
                modifier = Modifier.padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RulesetBadge(ruleset = state.ruleset)
            }

            ControlsBar(
                phase = state.phase,
                onStart = onStart,
                onPause = onPause,
                onResume = onResume,
                onReset = onReset,
                onBack = onBack
            )

            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(ColorBorder))
        }

        // Player 2 panel
        PlayerPanel(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            playerLabel = "Player 2",
            timeMillis = state.playerTwoTimeMs,
            initialTimeMs = (state.ruleset.minutes * 60 * 1000L),
            moveCount = state.playerTwoMoves,
            isActive = state.activePlayer == Player.TWO,
            isFinished = state.phase == GamePhase.FINISHED,
            onTap = onPlayerTwoTap
        )
    }
}
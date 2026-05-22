package com.kingslayer06.clockapp.presentation.views.clock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kingslayer06.clockapp.core.ui.ColorBackground
import com.kingslayer06.clockapp.domain.models.ChessRuleset
import com.kingslayer06.clockapp.domain.models.ClockUiState
import com.kingslayer06.clockapp.domain.models.GamePhase
import com.kingslayer06.clockapp.domain.models.Player
import com.kingslayer06.clockapp.presentation.viewModels.ClockViewModel
import com.kingslayer06.clockapp.presentation.views.clock.components.LandscapeClockLayout
import com.kingslayer06.clockapp.presentation.views.clock.components.PortraitClockLayout
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ClockView(
    ruleset: ChessRuleset,
    onBack: () -> Unit,
    viewModel: ClockViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(ruleset) {
        if (state.phase == GamePhase.IDLE) {
            viewModel.selectRuleset(ruleset)
        }
    }

    ClockViewContent(
        state = state,
        onPlayerOneTap = { viewModel.handlePlayerTap(Player.ONE) },
        onPlayerTwoTap = { viewModel.handlePlayerTap(Player.TWO) },
        onStart = { viewModel.startClock() },
        onPause = { viewModel.pauseClock() },
        onResume = { viewModel.resumeClock() },
        onReset = { viewModel.resetClock(ruleset) },
        onBack = onBack
    )
}

@Composable
private fun ClockViewContent(
    state: ClockUiState,
    onPlayerOneTap: () -> Unit,
    onPlayerTwoTap: () -> Unit,
    onStart: () -> Unit,
    onPause: () -> Unit,
    onResume: () -> Unit,
    onReset: () -> Unit,
    onBack: () -> Unit,
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground)
    ) {
        val isLandscape = maxWidth > maxHeight

        if (isLandscape) {
            LandscapeClockLayout(
                state = state,
                onPlayerOneTap = onPlayerOneTap,
                onPlayerTwoTap = onPlayerTwoTap,
                onStart = onStart,
                onPause = onPause,
                onResume = onResume,
                onReset = onReset,
                onBack = onBack
            )
        } else {
            PortraitClockLayout(
                state = state,
                onPlayerOneTap = onPlayerOneTap,
                onPlayerTwoTap = onPlayerTwoTap,
                onStart = onStart,
                onPause = onPause,
                onResume = onResume,
                onReset = onReset,
                onBack = onBack
            )
        }
    }
}

@Preview
@Composable
fun ClockViewPreview() {
    ClockViewContent(
        state = ClockUiState(),
        onPlayerOneTap = {},
        onPlayerTwoTap = {},
        onStart = {},
        onPause = {},
        onResume = {},
        onReset = {},
        onBack = {}
    )
}
package com.kingslayer06.clockapp.presentation.views.clock.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kingslayer06.clockapp.core.ui.ColorAccentGreen
import com.kingslayer06.clockapp.core.ui.ColorDanger
import com.kingslayer06.clockapp.core.ui.ColorSurface
import com.kingslayer06.clockapp.domain.models.GamePhase

@Composable
fun ControlsBar(
    phase: GamePhase,
    onStart: () -> Unit,
    onPause: () -> Unit,
    onResume: () -> Unit,
    onReset: () -> Unit,
    onBack: () -> Unit
) {
    Surface(
        color  = ColorSurface,
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            when (phase) {
                GamePhase.IDLE -> {
                    ControlButton(label = "Start", accent = ColorAccentGreen, onClick = onStart)
                }
                GamePhase.RUNNING -> {
                    ControlButton(label = "Pause", onClick = onPause)
                }
                GamePhase.PAUSED -> {
                    ControlButton(label = "Resume", accent = ColorAccentGreen, onClick = onResume)
                }
                GamePhase.FINISHED -> {
                    // Only reset available
                }
                else -> Unit
            }

            if (phase != GamePhase.IDLE) {
                ControlButton(label = "Reset", accent = ColorDanger, onClick = onReset)
            }

            ControlButton(label = "Exit", onClick = onBack)
        }
    }
}
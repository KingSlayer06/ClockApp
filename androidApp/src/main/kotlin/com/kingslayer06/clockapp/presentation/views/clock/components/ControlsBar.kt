package com.kingslayer06.clockapp.presentation.views.clock.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kingslayer06.clockapp.core.ui.ColorAccentGreen
import com.kingslayer06.clockapp.core.ui.ColorDanger
import com.kingslayer06.clockapp.core.ui.ColorSurface
import com.kingslayer06.clockapp.core.ui.ColorTextSecondary
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

@Composable
private fun ControlButton(
    label: String,
    accent: Color = ColorTextSecondary,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = accent),
        border = BorderStroke(1.dp, accent),
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 5.dp),
        modifier = Modifier.height(30.dp)
    ) {
        Text(text = label, fontSize = 12.sp, fontWeight = FontWeight.Normal)
    }
}
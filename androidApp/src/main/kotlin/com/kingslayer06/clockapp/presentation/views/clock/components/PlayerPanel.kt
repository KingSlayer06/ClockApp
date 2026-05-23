package com.kingslayer06.clockapp.presentation.views.clock.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kingslayer06.clockapp.core.ui.ColorActive
import com.kingslayer06.clockapp.core.ui.ColorDanger
import com.kingslayer06.clockapp.core.ui.ColorInactive
import com.kingslayer06.clockapp.core.ui.ColorTextHint
import com.kingslayer06.clockapp.core.ui.ColorTextSecondary
import com.kingslayer06.clockapp.core.ui.ColorWarning

@Composable
fun PlayerPanel(
    modifier: Modifier,
    playerLabel: String,
    timeMillis: Long,
    initialTimeMs: Long,
    moveCount: Int,
    isActive: Boolean,
    isFinished: Boolean,
    onTap: () -> Unit
) {
    val haptic = LocalHapticFeedback.current

    val ratio = (timeMillis.toFloat() / initialTimeMs.toFloat()).coerceIn(0f, 1f)

    val bgColor by animateColorAsState(
        targetValue = when {
            !isActive -> ColorInactive
            ratio > 0.3f -> {
                val fraction = (ratio - 0.3f) / 0.7f
                lerp(ColorWarning, ColorActive, fraction)
            }
            else -> {
                val fraction = ratio / 0.3f
                lerp(ColorDanger, ColorWarning, fraction)
            }
        },
        animationSpec = tween(durationMillis = 500),
        label = "panelBg"
    )

    val panelAlpha by animateFloatAsState(
        targetValue = if (!isActive && !isFinished) 0.55f else 1f,
        animationSpec = tween(200),
        label = "panelAlpha"
    )

    Box(
        modifier = modifier
            .background(bgColor)
            .alpha(panelAlpha)
            .clickable(
                enabled = isActive,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onTap()
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Player label
            Text(
                text = playerLabel.uppercase(),
                color = ColorTextSecondary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.5.sp
            )

            // Timer
            TimerDisplay(timeMillis = timeMillis, isActive = isActive)

            // Move counter
            Text(
                text = "$moveCount moves".uppercase(),
                color = ColorTextSecondary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.5.sp
            )

            // Tap hint
            if (isActive) {
                Text(
                    text = "Tap to end your turn",
                    color = ColorTextHint,
                    fontSize = 11.sp,
                    letterSpacing = 1.5.sp
                )
            }
        }
    }
}
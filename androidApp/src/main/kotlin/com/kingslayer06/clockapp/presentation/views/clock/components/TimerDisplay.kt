package com.kingslayer06.clockapp.presentation.views.clock.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kingslayer06.clockapp.core.ui.ColorTextPrimary
import com.kingslayer06.clockapp.core.ui.ColorTextSecondary
import com.kingslayer06.clockapp.core.utils.formatTime

@Composable
fun TimerDisplay(timeMillis: Long, isActive: Boolean) {
    val textColor by animateColorAsState(
        targetValue = when {
            isActive -> ColorTextPrimary
            else -> ColorTextSecondary
        },
        animationSpec = tween(300),
        label = "timerColor"
    )

    Text(
        text = formatTime(timeMillis),
        color = textColor,
        fontSize = 56.sp,
        fontWeight = FontWeight.Light,
        letterSpacing = (-2).sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.widthIn(min = 200.dp)
    )
}
package com.kingslayer06.clockapp.presentation.views.settings.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kingslayer06.clockapp.core.ui.ColorAccentGreen
import com.kingslayer06.clockapp.core.ui.ColorBorder
import com.kingslayer06.clockapp.core.ui.ColorSurface
import com.kingslayer06.clockapp.core.ui.ColorTextPrimary
import com.kingslayer06.clockapp.core.ui.ColorTextSecondary
import com.kingslayer06.clockapp.domain.models.ChessRuleset
import com.kingslayer06.clockapp.domain.models.SettingsUiState

@Composable
fun CustomRulesetGrid(
    state: SettingsUiState,
    onDecrementMinutes: () -> Unit,
    onIncrementMinutes: () -> Unit,
    onDecrementIncrement: () -> Unit,
    onIncrementIncrement: () -> Unit
) {
    val isCustomSelected = state.selectedRuleset is ChessRuleset.Custom

    val customBorderColor by animateColorAsState(
        targetValue = if (isCustomSelected) ColorAccentGreen else ColorBorder,
        label = "presetBorder"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = customBorderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CustomTimeStepper(
            label = "Minutes per player",
            value = state.selectedRuleset.minutes,
            range = 1..60,
            isSelected = isCustomSelected,
            onDecrement = onDecrementMinutes,
            onIncrement = onIncrementMinutes
        )

        CustomTimeStepper(
            label = "Increment (seconds)",
            value = state.selectedRuleset.increment,
            range = 0..60,
            isSelected = isCustomSelected,
            onDecrement = onDecrementIncrement,
            onIncrement = onIncrementIncrement
        )
    }
}

@Composable
fun CustomTimeStepper(
    label: String,
    value: Int,
    range: IntRange,
    isSelected: Boolean,
    onDecrement: () -> Unit,
    onIncrement: () -> Unit
) {
    val bgColor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFF0D1F0D) else ColorSurface,
        label = "presetBg"
    )

    val textColor by animateColorAsState(
        targetValue = if (isSelected) ColorAccentGreen else ColorTextPrimary,
        label = "presetTextColor"
    )

    Surface(
        color = bgColor,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                color = ColorTextPrimary,
                fontSize = 12.sp,
                modifier = Modifier.weight(1f)
            )

            // Stepper
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                StepButton(label = "−", enabled = value > range.first, onClick = onDecrement)
                Text(
                    text = value.toString(),
                    color = textColor,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.widthIn(min = 28.dp),
                    textAlign = TextAlign.Center
                )
                StepButton(label = "+", enabled = value < range.last, onClick = onIncrement)
            }
        }
    }
}

@Composable
private fun StepButton(label: String, enabled: Boolean, onClick: () -> Unit) {
    Surface(
        color = Color(0xFF222222),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .size(26.dp)
            .clip(RoundedCornerShape(5.dp))
            .clickable(enabled = enabled, onClick = onClick)
            .alpha(if (enabled) 1f else 0.3f)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(label, color = ColorTextSecondary, fontSize = 14.sp)
        }
    }
}
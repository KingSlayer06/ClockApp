package com.kingslayer06.clockapp.presentation.views.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kingslayer06.clockapp.core.ui.ColorSurface
import com.kingslayer06.clockapp.core.ui.ColorTextPrimary
import com.kingslayer06.clockapp.core.ui.ColorTextSecondary

@Composable
fun CustomTimeStepper(
    label: String,
    value: Int,
    range: IntRange,
    onDecrement: () -> Unit,
    onIncrement: () -> Unit
) {
    Surface(
        color  = ColorSurface,
        shape  = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier          = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(label, color = ColorTextSecondary, fontSize = 12.sp, modifier = Modifier.weight(1f))

            // Stepper
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                StepButton(label = "−", enabled = value > range.first, onClick = onDecrement)
                Text(
                    text     = value.toString(),
                    color    = ColorTextPrimary,
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
        color    = Color(0xFF222222),
        shape    = RoundedCornerShape(5.dp),
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
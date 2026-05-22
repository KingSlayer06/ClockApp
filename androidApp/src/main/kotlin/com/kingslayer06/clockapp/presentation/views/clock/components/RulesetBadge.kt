package com.kingslayer06.clockapp.presentation.views.clock.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kingslayer06.clockapp.core.ui.ColorTextSecondary
import com.kingslayer06.clockapp.domain.models.ChessRuleset

@Composable
fun RulesetBadge(ruleset: ChessRuleset, modifier: Modifier = Modifier) {
    val (icon, label) = when (ruleset) {
        ChessRuleset.Blitz -> Icons.Default.FlashOn to "Blitz"
        ChessRuleset.Quick -> Icons.Default.Timer to "Quick"
        ChessRuleset.Action -> Icons.Default.Bolt to "Action"
        else -> Icons.Default.Schedule to "Custom"
    }

    Surface(
        modifier = modifier.padding(top = 8.dp),
        shape = RoundedCornerShape(20.dp),
        color = Color(0xFF1E1E3A),
        tonalElevation = 0.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Image(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp, start = 12.dp)
                    .size(15.dp),
                colorFilter = ColorFilter.tint(ColorTextSecondary),
            )

            Text(
                text = label,
                color = Color(0xFF9090CC),
                fontSize = 10.sp,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, end = 12.dp)
            )
        }
    }
}
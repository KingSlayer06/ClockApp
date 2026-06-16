package com.kingslayer06.clockapp.presentation.views.settings.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kingslayer06.clockapp.core.ui.ColorAccentGreen
import com.kingslayer06.clockapp.core.ui.ColorBorder
import com.kingslayer06.clockapp.core.ui.ColorSurface
import com.kingslayer06.clockapp.core.ui.ColorTextPrimary
import com.kingslayer06.clockapp.core.ui.ColorTextSecondary
import com.kingslayer06.clockapp.domain.models.ChessRuleset

@Composable
fun RulesetGrid(
    selected: ChessRuleset?,
    onSelect: (ChessRuleset) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        listOf(
            Triple(ChessRuleset.Blitz, "${ChessRuleset.Blitz.name} chess", "${ChessRuleset.Blitz.minutes}m"),
            Triple(ChessRuleset.Quick, "${ChessRuleset.Quick.name} chess", "${ChessRuleset.Quick.minutes}m"),
            Triple(ChessRuleset.Action, "${ChessRuleset.Action.name} chess", "${ChessRuleset.Action.minutes}m"),
        ).forEach { (ruleset, label, time) ->
            PresetCard(
                modifier = Modifier.weight(1f),
                type = label,
                time = time,
                isSelected = selected == ruleset,
                onClick = { onSelect(ruleset) }
            )
        }
    }
}

@Composable
private fun PresetCard(
    modifier: Modifier,
    type: String,
    time: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) ColorAccentGreen else ColorBorder,
        label = "presetBorder"
    )
    val bgColor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFF0D1F0D) else ColorSurface,
        label = "presetBg"
    )
    val textColor by animateColorAsState(
        targetValue = if (isSelected) ColorAccentGreen else ColorTextPrimary,
        label = "presetTextColor"
    )

    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick),
        color = bgColor,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = type.uppercase(),
                color = ColorTextSecondary,
                fontSize = 9.sp,
                letterSpacing = 1.sp
            )
            Text(
                text = time,
                color = textColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = (-0.5).sp
            )
        }
    }
}
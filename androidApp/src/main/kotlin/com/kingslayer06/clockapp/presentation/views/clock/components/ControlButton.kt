package com.kingslayer06.clockapp.presentation.views.clock.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kingslayer06.clockapp.core.ui.ColorTextSecondary

@Composable
fun ControlButton(
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
package com.kingslayer06.clockapp.presentation.views.settings

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kingslayer06.clockapp.core.ui.ColorAccentGreen
import com.kingslayer06.clockapp.core.ui.ColorBackground
import com.kingslayer06.clockapp.core.ui.ColorTextPrimary
import com.kingslayer06.clockapp.domain.models.ChessRuleset
import com.kingslayer06.clockapp.domain.models.SettingsUiState
import com.kingslayer06.clockapp.presentation.viewModels.SettingsViewModel
import com.kingslayer06.clockapp.presentation.views.settings.components.CustomRulesetGrid
import com.kingslayer06.clockapp.presentation.views.settings.components.RulesetGrid
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsScreen(
    onStart: (ChessRuleset) -> Unit,
    viewModel: SettingsViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    SettingsScreenContent(
        state = state,
        onSelectRuleset = { viewModel.selectRuleset(it) },
        onDecrementMinutes = { viewModel.decrementMinutes() },
        onIncrementMinutes = { viewModel.incrementMinutes() },
        onDecrementIncrement = { viewModel.decrementIncrement() },
        onIncrementIncrement = { viewModel.incrementIncrement() },
        onStartGame = { onStart(state.selectedRuleset) }
    )
}

@Composable
private fun SettingsScreenContent(
    state: SettingsUiState,
    onSelectRuleset: (ChessRuleset) -> Unit,
    onDecrementMinutes: () -> Unit,
    onIncrementMinutes: () -> Unit,
    onDecrementIncrement: () -> Unit,
    onIncrementIncrement: () -> Unit,
    onStartGame: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = ColorBackground
    ) {
        val configuration = LocalConfiguration.current
        val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(vertical = if (isLandscape) 24.dp else 48.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            // Header
            SettingsHeader()

            // Chess type presets
            SectionLabel("Chess type")
            RulesetGrid(
                selected = state.selectedRuleset,
                onSelect = onSelectRuleset
            )

            // Custom time
            SectionLabel("Custom time")
            CustomRulesetGrid(
                state = state,
                onDecrementMinutes = onDecrementMinutes,
                onIncrementMinutes = onIncrementMinutes,
                onDecrementIncrement = onDecrementIncrement,
                onIncrementIncrement = onIncrementIncrement
            )

            Spacer(modifier = Modifier.weight(1f))

            // Start button
            Button(
                onClick = onStartGame,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ColorAccentGreen)
            ) {
                Text(
                    text = "Start game",
                    color = Color(0xFF0A1F0A),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun SettingsHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.weight(1f))
        Text("Clock Settings", color = ColorTextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Medium)
        Spacer(Modifier.weight(1f))
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text.uppercase(),
        color = ColorTextPrimary,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 1.sp
    )
}

@Preview
@Composable
fun SettingsScreenPreview() {
    val dummyState = SettingsUiState(
        selectedRuleset = ChessRuleset.Blitz
    )

    SettingsScreenContent(
        state = dummyState,
        onSelectRuleset = {},
        onDecrementMinutes = {},
        onIncrementMinutes = {},
        onDecrementIncrement = {},
        onIncrementIncrement = {},
        onStartGame = {}
    )
}
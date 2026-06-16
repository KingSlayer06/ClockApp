package com.kingslayer06.clockapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.kingslayer06.clockapp.domain.models.ChessRuleset
import com.kingslayer06.clockapp.domain.models.SettingsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SettingsViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState = _uiState.asStateFlow()

    private val MIN_MINUTES = 1
    private val MAX_MINUTES = 60
    private val MIN_INCREMENT = 0
    private val MAX_INCREMENT = 60

    fun selectRuleset(ruleset: ChessRuleset) {
        _uiState.update { it.copy(
            selectedRuleset = ruleset
        ) }
    }

    fun incrementMinutes() {
        val current = _uiState.value.selectedRuleset
            ?: ChessRuleset.Custom(MIN_MINUTES, MIN_INCREMENT)

        val minutes = current.minutes + 1
        val increment = current.increment
        updateCustomValues(minutes, increment)
    }

    fun decrementMinutes() {
        val current = _uiState.value.selectedRuleset
            ?: ChessRuleset.Custom(MIN_MINUTES, MIN_INCREMENT)

        val minutes = current.minutes - 1
        val increment = current.increment
        updateCustomValues(minutes, increment)
    }

    fun incrementIncrement() {
        val current = _uiState.value.selectedRuleset
            ?: ChessRuleset.Custom(MIN_MINUTES, MIN_INCREMENT)

        val minutes = current.minutes
        val increment = current.increment + 1
        updateCustomValues(minutes,increment)
    }

    fun decrementIncrement() {
        val current = _uiState.value.selectedRuleset
            ?: ChessRuleset.Custom(MIN_MINUTES, MIN_INCREMENT)

        val minutes = current.minutes
        val increment = current.increment - 1
        updateCustomValues(minutes, increment)
    }

    private fun updateCustomValues(minutes: Int, increment: Int) {
        if (minutes !in MIN_MINUTES..MAX_MINUTES) return
        if (increment !in MIN_INCREMENT..MAX_INCREMENT) return

        _uiState.update { it.copy(
            selectedRuleset = ChessRuleset.Custom(minutes, increment),
        ) }
    }
}
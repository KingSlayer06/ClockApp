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

    init {
        selectRuleset(ChessRuleset.Blitz)
    }

    fun selectRuleset(ruleset: ChessRuleset) {
        _uiState.update { it.copy(
            selectedRuleset = ruleset
        ) }
    }

    fun incrementMinutes() {
        val minutes = _uiState.value.selectedRuleset.minutes + 1
        val increment = _uiState.value.selectedRuleset.increment
        updateCustomValues(minutes, increment)
    }

    fun decrementMinutes() {
        val minutes = _uiState.value.selectedRuleset.minutes - 1
        val increment = _uiState.value.selectedRuleset.increment
        updateCustomValues(minutes, increment)
    }

    fun incrementIncrement() {
        val minutes = _uiState.value.selectedRuleset.minutes
        val increment = _uiState.value.selectedRuleset.increment + 1
        updateCustomValues(minutes,increment)
    }

    fun decrementIncrement() {
        val minutes = _uiState.value.selectedRuleset.minutes
        val increment = _uiState.value.selectedRuleset.increment - 1
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
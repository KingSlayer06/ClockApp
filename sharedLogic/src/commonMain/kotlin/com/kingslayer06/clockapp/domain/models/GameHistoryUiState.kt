package com.kingslayer06.clockapp.domain.models

data class GameHistoryUiState(
    val isLoading: Boolean = false,
    val gameHistory: List<GameHistory> = emptyList(),
    val error: String? = null
)

package com.kingslayer06.clockapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kingslayer06.clockapp.domain.models.GameHistoryUiState
import com.kingslayer06.clockapp.domain.useCase.DeleteByIdUseCase
import com.kingslayer06.clockapp.domain.useCase.GetAllUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameHistoryViewModel(
    private val getAllUseCase: GetAllUseCase,
    private val deleteByIdUseCase: DeleteByIdUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(GameHistoryUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getAll()
    }

    fun getAll() {
        viewModelScope.launch {
            getAllUseCase.execute()
                .onStart {
                    _uiState.update { it.copy(isLoading = true, error = null) }
                }
                .catch {
                    _uiState.update { it.copy(isLoading = false, error = "Failed to fetch data") }
                }
                .collect { history ->
                    _uiState.update { it.copy(isLoading = false, gameHistory = history) }
                }
        }
    }

    fun deleteGame(id: Long) {
        viewModelScope.launch {
            try {
                deleteByIdUseCase.execute(id)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Failed to delete data") }
            }
        }
    }
}

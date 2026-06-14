package com.kingslayer06.clockapp.domain.repository

import com.kingslayer06.clockapp.domain.models.GameHistory
import kotlinx.coroutines.flow.Flow

interface GameHistoryRepository {
    suspend fun insert(game: GameHistory)
    fun getAll(): Flow<List<GameHistory>>
    suspend fun getById(id: Long): GameHistory?
    suspend fun deleteById(id: Long)
}

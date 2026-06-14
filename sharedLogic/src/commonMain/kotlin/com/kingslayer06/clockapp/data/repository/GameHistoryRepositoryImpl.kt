package com.kingslayer06.clockapp.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.kingslayer06.clockapp.database.ClockDatabase
import com.kingslayer06.clockapp.domain.models.GameHistory
import com.kingslayer06.clockapp.domain.repository.GameHistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GameHistoryRepositoryImpl(
    db: ClockDatabase
) : GameHistoryRepository {
    private val queries = db.clockDatabaseQueries

    override suspend fun insert(game: GameHistory) {
        withContext(Dispatchers.IO) {
            queries.insert(
                rulesetName = game.rulesetName,
                minutes = game.minutes.toLong(),
                increment = game.increment.toLong(),
                playerOneMoves = game.playerOneMoves.toLong(),
                playerTwoMoves = game.playerTwoMoves.toLong(),
                playerOneTimeMs = game.playerOneTimeMs,
                playerTwoTimeMs = game.playerTwoTimeMs,
                winner = game.winner,
                date = game.date
            )
        }
    }

    override fun getAll(): Flow<List<GameHistory>> {
        return queries.selectAll { id, rulesetName, minutes, increment,
                                   playerOneMoves, playerTwoMoves,
                                   playerOneTimeMs, playerTwoTimeMs,
                                   winner, date ->
            GameHistory(
                id = id,
                rulesetName = rulesetName,
                minutes = minutes.toInt(),
                increment = increment.toInt(),
                playerOneMoves = playerOneMoves.toInt(),
                playerTwoMoves = playerTwoMoves.toInt(),
                playerOneTimeMs = playerOneTimeMs,
                playerTwoTimeMs = playerTwoTimeMs,
                winner = winner,
                date = date
            )
        }.asFlow().mapToList(Dispatchers.IO)
    }

    override suspend fun getById(id: Long): GameHistory? {
        return withContext(Dispatchers.IO) {
            queries.selectById(id) { id, rulesetName, minutes, increment,
                                     playerOneMoves, playerTwoMoves,
                                     playerOneTimeMs, playerTwoTimeMs,
                                     winner, date ->
                GameHistory(
                    id = id,
                    rulesetName = rulesetName,
                    minutes = minutes.toInt(),
                    increment = increment.toInt(),
                    playerOneMoves = playerOneMoves.toInt(),
                    playerTwoMoves = playerTwoMoves.toInt(),
                    playerOneTimeMs = playerOneTimeMs,
                    playerTwoTimeMs = playerTwoTimeMs,
                    winner = winner,
                    date = date
                )
            }.executeAsOneOrNull()
        }
    }

    override suspend fun deleteById(id: Long) {
        withContext(Dispatchers.IO) {
            queries.deleteById(id)
        }
    }
}

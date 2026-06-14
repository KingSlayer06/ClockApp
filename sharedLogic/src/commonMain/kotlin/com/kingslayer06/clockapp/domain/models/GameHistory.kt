package com.kingslayer06.clockapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class GameHistory(
    val id: Long? = null,
    val rulesetName: String,
    val minutes: Int,
    val increment: Int,
    val playerOneMoves: Int,
    val playerTwoMoves: Int,
    val playerOneTimeMs: Long,
    val playerTwoTimeMs: Long,
    val winner: String? = null,
    val date: Long
)

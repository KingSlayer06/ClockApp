package com.kingslayer06.clockapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
sealed class ChessRuleset(
    val name: String,
    val minutes: Int,
    val increment: Int = 0
) {
    @Serializable
    data object Blitz : ChessRuleset(
        name ="Blitz",
        minutes = 5, // 5 mins
    )

    @Serializable
    data object Quick : ChessRuleset(
        name = "Quick",
        minutes = 15, // 15 mins
    )

    @Serializable
    data object Action : ChessRuleset(
        name = "Action",
        minutes = 30, // 30 mins
    )

    @Serializable
    data class Custom(
        val customMinutes: Int = 1,
        val customIncrement: Int = 0
    ) : ChessRuleset(
        name ="Custom",
        minutes = customMinutes,
        increment = customIncrement
    )
}
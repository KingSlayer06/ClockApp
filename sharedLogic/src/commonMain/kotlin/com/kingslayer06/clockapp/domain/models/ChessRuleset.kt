package com.kingslayer06.clockapp.domain.models

sealed class ChessRuleset(
    val name: String,
    val minutes: Int,
    val increment: Int = 0
) {
    object Blitz : ChessRuleset(
        name ="Blitz",
        minutes = 5, // 5 mins
    )

    object Quick : ChessRuleset(
        name = "Quick",
        minutes = 15, // 15 mins
    )

    object Action : ChessRuleset(
        name = "Action",
        minutes = 30, // 30 mins
    )

    class Custom(
        minutes: Int,
        increment: Int = 0
    ) : ChessRuleset(
        name ="Custom",
        minutes = minutes,
        increment = increment
    )
}
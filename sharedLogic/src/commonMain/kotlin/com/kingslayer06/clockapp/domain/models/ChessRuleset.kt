package com.kingslayer06.clockapp.domain.models

sealed class ChessRuleset(
    val name: String,
    val initialTimeMillis: Long,
    val incrementMillis: Long = 0L
) {
    object Blitz : ChessRuleset(
        name ="Blitz",
        initialTimeMillis = 5 * 60 * 1000L, // 5 mins,
        incrementMillis = 0
    )

    object Quick : ChessRuleset(
        name = "Quick",
        initialTimeMillis = 15 * 60 * 1000L, // 15 mins
        incrementMillis = 0
    )

    object Action : ChessRuleset(
        name = "Action",
        initialTimeMillis = 30 * 60 * 1000L, // 30 mins
        incrementMillis = 0
    )

    class Custom(
        timeMillis: Long,
        incrementMillis: Long = 0L
    ) : ChessRuleset(
        name ="Custom",
        initialTimeMillis = timeMillis,
        incrementMillis = incrementMillis
    )
}
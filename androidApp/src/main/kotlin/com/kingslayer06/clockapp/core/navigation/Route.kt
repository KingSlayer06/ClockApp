package com.kingslayer06.clockapp.core.navigation

import com.kingslayer06.clockapp.domain.models.ChessRuleset
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Settings : Route

    @Serializable
    data class Clock(val ruleset: ChessRuleset) : Route
}
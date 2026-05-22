package com.kingslayer06.clockapp.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kingslayer06.clockapp.core.navigation.ChessRulesetType
import com.kingslayer06.clockapp.core.navigation.Route
import com.kingslayer06.clockapp.domain.models.ChessRuleset
import com.kingslayer06.clockapp.presentation.views.clock.ClockView
import com.kingslayer06.clockapp.presentation.views.settings.SettingsScreen
import kotlin.reflect.typeOf

@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Route.Settings
        ) {
            composable<Route.Settings> {
                SettingsScreen(
                    onStart = { selectedRuleset ->
                        navController.navigate(Route.Clock(selectedRuleset))
                    }
                )
            }
            composable<Route.Clock>(
                typeMap = mapOf(
                    typeOf<ChessRuleset>() to ChessRulesetType
                )
            ) {
                val route = it.toRoute<Route.Clock>()
                ClockView(
                    ruleset = route.ruleset,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
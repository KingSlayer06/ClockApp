//
//  LandscapeClockLayout.swift
//  iosApp
//
//  Created by Himanshu Sherkar on 22/05/26.
//

import SwiftUI
import SharedLogic

struct LandscapeClockLayout: View {
    let state: ClockUiState
    let onPlayerOneTap: () -> Void
    let onPlayerTwoTap: () -> Void
    let onStart: () -> Void
    let onPause: () -> Void
    let onResume: () -> Void
    let onReset: () -> Void
    let onBack: () -> Void
    
    var body: some View {
        VStack(spacing: 0) {
            RulesetBadge(ruleset: state.ruleset)
            
            // Player 1 panel rotated 180 degrees to face the player
            HStack(spacing: 0) {
                PlayerPanel(
                    playerLabel: "Player 1",
                    timeMs: state.playerOneTimeMs,
                    initialTimeMs: Int64(state.ruleset.minutes) * 60_000,
                    moveCount: Int(state.playerOneMoves),
                    isActive: state.activePlayer == .one,
                    isFinished: state.phase == .finished,
                    onTap: onPlayerOneTap
                )
                .rotationEffect(.degrees(180))
                
                // Divider
                Divider()
                    .frame(width: 1)
                    .background(Color.border)
                
                // Player 2 panel
                PlayerPanel(
                    playerLabel: "Player 2",
                    timeMs: state.playerTwoTimeMs,
                    initialTimeMs: Int64(state.ruleset.minutes) * 60_000,
                    moveCount: Int(state.playerTwoMoves),
                    isActive: state.activePlayer == .two,
                    isFinished: state.phase == .finished,
                    onTap: onPlayerTwoTap
                )
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            
            // Controls
            ControlsBar(
                phase: state.phase,
                onStart: onStart,
                onPause: onPause,
                onResume: onResume,
                onReset: onReset,
                onBack: onBack
            )
        }
    }
}

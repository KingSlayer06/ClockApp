//
//  ClockView.swift
//  iosApp
//
//  Created by Himanshu Sherkar on 22/05/26.
//

import SwiftUI
import SharedLogic

struct ClockView: View {
    let ruleset: ChessRuleset
    let onBack: () -> Void
    
    @StateObject private var viewModel = ClockViewModelWrapper()
    
    var body: some View {
        Group {
            ClockViewContent(
                state: viewModel.uiState,
                onPlayerOneTap: { viewModel.handlePlayerTap(player: .one) },
                onPlayerTwoTap: { viewModel.handlePlayerTap(player: .two) },
                onStart: { viewModel.startClock() },
                onPause: { viewModel.pauseClock() },
                onResume: { viewModel.resumeClock() },
                onReset: { viewModel.resetClock(ruleset: ruleset) },
                onBack: onBack
            )
        }
    }
}

private struct ClockViewContent: View {
    let state: ClockUiState
    let onPlayerOneTap: () -> Void
    let onPlayerTwoTap: () -> Void
    let onStart: () -> Void
    let onPause: () -> Void
    let onResume: () -> Void
    let onReset: () -> Void
    let onBack: () -> Void
    
    var body: some View {
        
    }
}

#Preview {
    let dummyState = ClockUiState(
        phase: .running,
        activePlayer: .one,
        playerOneTimeMs: 15,
        playerTwoTimeMs: 15,
        playerOneMoves: 10,
        playerTwoMoves: 10,
        ruleset: .Blitz(),
        winner: nil,
        playerOneTimeState: .normal,
        playerTwoTimeState: .normal
    )
    
    ClockViewContent(
        state: dummyState,
        onPlayerOneTap: {},
        onPlayerTwoTap: {},
        onStart: {},
        onPause: {},
        onResume: {},
        onReset: {},
        onBack: {}
    )
}

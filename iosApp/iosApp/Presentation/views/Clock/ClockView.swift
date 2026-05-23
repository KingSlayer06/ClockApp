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
        ZStack {
            if (viewModel.uiState.phase == .finished) {
                GameOverAlert(
                    winner: viewModel.uiState.winner,
                    onDismiss: onBack
                )
            }
            
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
        .onAppear {
            if viewModel.uiState.phase == .idle {
                viewModel.selectRuleset(ruleset: ruleset)
            }
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
    
    @Environment(\.horizontalSizeClass) private var hSize
    
    var body: some View {
        GeometryReader { geometry in
            let isLandscape = geometry.size.width > geometry.size.height
            
            if isLandscape {
                LandscapeClockLayout(
                    state: state,
                    onPlayerOneTap: onPlayerOneTap,
                    onPlayerTwoTap: onPlayerTwoTap,
                    onStart: onStart,
                    onPause: onPause,
                    onResume: onResume,
                    onReset: onReset,
                    onBack: onBack
                )
            } else {
                PortraitClockLayout(
                    state: state,
                    onPlayerOneTap: onPlayerOneTap,
                    onPlayerTwoTap: onPlayerTwoTap,
                    onStart: onStart,
                    onPause: onPause,
                    onResume: onResume,
                    onReset: onReset,
                    onBack: onBack
                )
            }
        }
        .background(Color.background.ignoresSafeArea())
        .navigationBarHidden(true)
        .ignoresSafeArea()
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
        winner: nil
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

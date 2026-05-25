//
//  SettingsView.swift
//  iosApp
//
//  Created by Himanshu Sherkar on 22/05/26.
//

import SwiftUI
import SharedLogic

struct SettingsView: View {
    @StateObject private var viewModel = SettingsViewModelWrapper()
    
    let onStart: (ChessRuleset) -> Void
    
    var body: some View {
        SettingsViewContent(
            state: viewModel.uiState,
            onSelectRuleset: { viewModel.selectRuleset(ruleset: $0) },
            onDecrementMinutes: { viewModel.decrementMinutes() },
            onIncrementMinutes: { viewModel.incrementMinutes() },
            onDecrementIncrement: { viewModel.decrementIncrement() },
            onIncrementIncrement: { viewModel.incrementIncrement() },
            onStartGame: { onStart(viewModel.uiState.selectedRuleset) }
        )
    }
}

private struct SettingsViewContent: View {
    let state: SettingsUiState
    let onSelectRuleset: (ChessRuleset) -> Void
    let onDecrementMinutes: () -> Void
    let onIncrementMinutes: () -> Void
    let onDecrementIncrement: () -> Void
    let onIncrementIncrement: () -> Void
    let onStartGame: () -> Void
    
    var body: some View {
        VStack(alignment: .leading, spacing: 15) {
            // Header
            SettingsHeader()
            
            // Ruleset grid
            SectionLabel(text: "Chess type")
            RulesetGrid(
                selected: state.selectedRuleset,
                onSelect: onSelectRuleset
            )
            
            // Custom time
            SectionLabel(text: "Custom time")
            CustomRulesetGrid(
                state: state,
                onDecrementMinutes: onDecrementMinutes,
                onIncrementMinutes: onIncrementMinutes,
                onDecrementIncrement: onDecrementIncrement,
                onIncrementIncrement: onIncrementIncrement
            )
            
            Spacer(minLength: 0)
            
            // Start button
            Button {
                onStartGame()
            } label: {
                Text("Start game")
                    .font(.system(size: 14, weight: .semibold))
                    .foregroundColor(Color(hex: "0A1F0A"))
                    .frame(maxWidth: .infinity)
                    .frame(height: 48)
                    .background(Color.accentGreen)
                    .clipShape(RoundedRectangle(cornerRadius: 10))
            }
        }
        .padding(.horizontal, 24)
        .padding(.vertical, 20)
        .background(Color.background.ignoresSafeArea())
        .navigationBarHidden(true)
    }
}

private struct SettingsHeader: View {
    var body: some View {
        HStack {
            Spacer()
            Text("Clock Settings")
                .font(.system(size: 15, weight: .medium))
                .foregroundColor(Color.textPrimary)
            Spacer()
        }
    }
}

private struct SectionLabel: View {
    let text: String
    
    var body: some View {
        Text(text.uppercased())
            .font(.system(size: 11))
            .fontWeight(Font.Weight.medium)
            .foregroundColor(Color.textPrimary)
            .tracking(1.0)
    }
}

#Preview {
    let dummyState = SettingsUiState(selectedRuleset: .Blitz())
    
    SettingsViewContent(
        state: dummyState,
        onSelectRuleset: { _ in },
        onDecrementMinutes: {},
        onIncrementMinutes: {},
        onDecrementIncrement: {},
        onIncrementIncrement: {},
        onStartGame: {}
    )
}

//
//  CustomTimeStepper.swift
//  iosApp
//
//  Created by Himanshu Sherkar on 22/05/26.
//

import SwiftUI
import SharedLogic

struct CustomRulesetGrid: View {
    let state: SettingsUiState
    let onDecrementMinutes: () -> Void
    let onIncrementMinutes: () -> Void
    let onDecrementIncrement: () -> Void
    let onIncrementIncrement: () -> Void

    @Environment(\.verticalSizeClass) var vSize

    var isLandscape: Bool {
        vSize == .compact
    }
    
    var isCustomSelected: Bool {
        state.selectedRuleset is ChessRuleset.Custom
    }
    
    var customBorderColor: Color {
        withAnimation {
            isCustomSelected ? Color.accentGreen : Color.border
        }
    }
    
    var body: some View {
        Group {
            if isLandscape {
                LandscapeCustomRulesetGrid(
                    state: state,
                    isCustomSelected: isCustomSelected,
                    onDecrementMinutes: onDecrementMinutes,
                    onIncrementMinutes: onIncrementMinutes,
                    onDecrementIncrement: onDecrementIncrement,
                    onIncrementIncrement: onIncrementIncrement
                )
            } else {
                PortraitCustomRulesetGrid(
                    state: state,
                    isCustomSelected: isCustomSelected,
                    onDecrementMinutes: onDecrementMinutes,
                    onIncrementMinutes: onIncrementMinutes,
                    onDecrementIncrement: onDecrementIncrement,
                    onIncrementIncrement: onIncrementIncrement
                )
            }
        }
        .padding(.horizontal, 24)
        .padding(.vertical, 20)
        .clipShape(RoundedRectangle(cornerRadius: 10))
        .overlay(
            RoundedRectangle(cornerRadius: 10)
                .stroke(customBorderColor, lineWidth: 1)
        )
    }
}

private struct LandscapeCustomRulesetGrid: View {
    let state: SettingsUiState
    let isCustomSelected: Bool
    let onDecrementMinutes: () -> Void
    let onIncrementMinutes: () -> Void
    let onDecrementIncrement: () -> Void
    let onIncrementIncrement: () -> Void

    var body: some View {
        HStack(spacing: 20) {
            CustomTimeStepper(
                label: "Minutes per player",
                value: state.selectedRuleset.minutes,
                range: 1...60,
                isSelected: isCustomSelected,
                onDecrement: onDecrementMinutes,
                onIncrement: onIncrementMinutes
            )
            CustomTimeStepper(
                label: "Increment (seconds)",
                value: state.selectedRuleset.increment,
                range: 0...60,
                isSelected: isCustomSelected,
                onDecrement: onDecrementIncrement,
                onIncrement: onIncrementIncrement
            )
        }
    }
}

private struct PortraitCustomRulesetGrid: View {
    let state: SettingsUiState
    let isCustomSelected: Bool
    let onDecrementMinutes: () -> Void
    let onIncrementMinutes: () -> Void
    let onDecrementIncrement: () -> Void
    let onIncrementIncrement: () -> Void

    var body: some View {
        VStack(spacing: 20) {
            CustomTimeStepper(
                label: "Minutes per player",
                value: state.selectedRuleset.minutes,
                range: 1...60,
                isSelected: isCustomSelected,
                onDecrement: onDecrementMinutes,
                onIncrement: onIncrementMinutes
            )
            CustomTimeStepper(
                label: "Increment (seconds)",
                value: state.selectedRuleset.increment,
                range: 0...60,
                isSelected: isCustomSelected,
                onDecrement: onDecrementIncrement,
                onIncrement: onIncrementIncrement
            )
        }
    }
}

private struct CustomTimeStepper: View {
    let label: String
    let value: Int32
    let range: ClosedRange<Int32>
    let isSelected: Bool
    let onDecrement: () -> Void
    let onIncrement: () -> Void
    
    var bgColor: Color {
        withAnimation {
            isSelected ? Color(hex: "#0D1F0D") : Color.surface
        }
    }
    
    var textColor: Color {
        withAnimation {
            isSelected ? Color.accentGreen : Color.textPrimary
        }
    }
    
    var body: some View {
        HStack {
            Text(label)
                .font(.system(size: 12))
                .foregroundColor(Color.textPrimary)
            
            Spacer()
            
            HStack(spacing: 4) {
                StepButton(label: "−", enabled: value > range.lowerBound, action: onDecrement)
                
                Text("\(value)")
                    .font(.system(size: 13, weight: .medium))
                    .foregroundColor(textColor)
                    .frame(minWidth: 28)
                    .multilineTextAlignment(.center)
                
                StepButton(label: "+", enabled: value < range.upperBound, action: onIncrement)
            }
        }
        .padding(.horizontal, 14)
        .padding(.vertical, 10)
        .background(bgColor)
        .clipShape(RoundedRectangle(cornerRadius: 10))
    }
}

private struct StepButton: View {
    let label: String
    let enabled: Bool
    let action: () -> Void
    
    var body: some View {
        Button(action: action) {
            Text(label)
                .font(.system(size: 14))
                .foregroundColor(Color.textSecondary)
                .frame(width: 26, height: 26)
                .background(Color(hex: "#222222"))
                .clipShape(RoundedRectangle(cornerRadius: 5))
        }
        .disabled(!enabled)
        .opacity(enabled ? 1 : 0.3)
    }
}

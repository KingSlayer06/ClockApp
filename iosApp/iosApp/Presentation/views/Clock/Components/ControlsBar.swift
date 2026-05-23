//
//  ControlsBar.swift
//  iosApp
//
//  Created by Himanshu Sherkar on 22/05/26.
//

import SwiftUI
import SharedLogic

struct ControlsBar: View {
    let phase: GamePhase
    let onStart: () -> Void
    let onPause: () -> Void
    let onResume: () -> Void
    let onReset: () -> Void
    let onBack: () -> Void
    
    var body: some View {
        HStack(spacing: 8) {
            Spacer()
            
            switch phase {
                case .idle:
                    ControlButton(label: "Start", accent: Color.accentGreen, action: onStart)
                case .running:
                    ControlButton(label: "Pause", action: onPause)
                case .paused:
                    ControlButton(label: "Resume", accent: Color.accentGreen, action: onResume)
                case .finished:
                    EmptyView()
            }
            
            if phase != .idle {
                ControlButton(label: "Reset", accent: Color.danger, action: onReset)
            }
            
            ControlButton(label: "Exit", action: onBack)
            
            Spacer()
        }
        .frame(height: 44)
        .background(Color.surface)
        .overlay(alignment: .top) {
            Rectangle()
                .frame(height: 0.5)
                .foregroundColor(Color.border)
        }
    }
}

private struct ControlButton: View {
    let label:  String
    let accent: Color
    let action: () -> Void
    
    init(label: String, accent: Color = .textPrimary, action: @escaping () -> Void) {
        self.label = label
        self.accent = accent
        self.action = action
    }
    
    var body: some View {
        Button(action: action) {
            Text(label)
                .font(.system(size: 12))
                .foregroundColor(accent)
                .padding(.horizontal, 14)
                .padding(.vertical, 5)
                .overlay(
                    RoundedRectangle(cornerRadius: 6)
                        .stroke(accent.opacity(0.5), lineWidth: 0.5)
                )
        }
    }
}

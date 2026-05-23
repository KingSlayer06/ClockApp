//
//  PlayerPanel.swift
//  iosApp
//
//  Created by Himanshu Sherkar on 22/05/26.
//

import SwiftUI

struct PlayerPanel: View {
    let playerLabel: String
    let timeMs: Int64
    let initialTimeMs: Int64
    let moveCount: Int
    let isActive: Bool
    let isFinished: Bool
    let onTap: () -> Void

    private var ratio: Double {
        Double(max(0, timeMs)) / Double(max(1, initialTimeMs))
    }
    
    var bgColor: Color {
        withAnimation(.easeInOut(duration: 0.5)) {
            if !isActive {
                return Color.inactive
            }

            if ratio > 0.3 {
                let fraction = (ratio - 0.3) / 0.7
                return Color.lerp(from: .warning, to: .active, fraction: fraction)
            } else {
                let fraction = ratio / 0.3
                return Color.lerp(from: .danger, to: .warning, fraction: fraction)
            }
        }
    }
    
    var body: some View {
        ZStack {
            Rectangle()
                .fill(bgColor)
            
            // Main content
            VStack(spacing: 10) {
                Text(playerLabel.uppercased())
                    .font(.system(size: 11, weight: .medium))
                    .foregroundColor(Color.textSecondary)
                    .tracking(1.5)
                
                TimerDisplay(timeMs: timeMs, isActive: isActive)
                
                if isActive {
                    Text("tap to end turn")
                        .font(.system(size: 10))
                        .foregroundColor(Color.textHint)
                        .tracking(0.8)
                }
            }

            VStack {
                Spacer()
                Text("\(moveCount) moves")
                    .font(.system(size: 10))
                    .foregroundColor(Color.textHint)
                    .padding(.bottom, 52)
            }
        }
        .opacity((!isActive && !isFinished) ? 0.55 : 1.0)
        .animation(.easeInOut(duration: 0.2), value: isActive)
        .contentShape(Rectangle())
        .onTapGesture {
            guard isActive else { return }
            let generator = UIImpactFeedbackGenerator(style: .medium)
            generator.impactOccurred()
            onTap()
        }
        .accessibilityLabel("\(playerLabel) clock: \(formatTime(ms: timeMs))")
    }
}

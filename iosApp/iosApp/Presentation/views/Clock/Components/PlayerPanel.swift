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
            
            VStack(spacing: 10) {
                // Player panel
                Text(playerLabel.uppercased())
                    .font(.system(size: 11, weight: .medium))
                    .foregroundColor(Color.textSecondary)
                    .tracking(1.5)
                
                // Timer
                TimerDisplay(timeMs: timeMs, isActive: isActive)
                
                // Move counter
                Text("\(moveCount) moves".uppercased())
                    .font(.system(size: 11, weight: .medium))
                    .foregroundColor(Color.textSecondary)
                    .tracking(1.5)
                
                if isActive {
                    Text("Tap to end your turn")
                        .font(.system(size: 11))
                        .foregroundColor(Color.textHint)
                        .tracking(1.5)
                }
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
    }
}

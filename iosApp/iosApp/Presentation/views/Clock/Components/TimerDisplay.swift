//
//  TimerDisplay.swift
//  iosApp
//
//  Created by Himanshu Sherkar on 22/05/26.
//

import SwiftUI

struct TimerDisplay: View {
    let timeMs:   Int64
    let isActive: Bool
    
    private var timerColor: Color {
        withAnimation(.easeInOut(duration: 0.3)) {
            isActive ? Color.textPrimary : Color.textSecondary
        }
    }
    
    var body: some View {
        Text(formatTime(ms: timeMs))
            .font(.system(size: 56, weight: .light, design: .monospaced))
            .foregroundColor(timerColor)
            .tracking(-2)
    }
}

//
//  CustomTimeStepper.swift
//  iosApp
//
//  Created by Himanshu Sherkar on 22/05/26.
//

import SwiftUI

struct CustomTimeStepper: View {
    let label: String
    let value: Int32
    let range: ClosedRange<Int32>
    let onDecrement: () -> Void
    let onIncrement: () -> Void
    
    var body: some View {
        HStack {
            Text(label)
                .font(.system(size: 12))
                .foregroundColor(Color.colorTextSecondary)
            
            Spacer()
            
            HStack(spacing: 4) {
                StepButton(label: "−", enabled: value > range.lowerBound, action: onDecrement)
                
                Text("\(value)")
                    .font(.system(size: 13, weight: .medium))
                    .foregroundColor(Color.colorTextPrimary)
                    .frame(minWidth: 28)
                    .multilineTextAlignment(.center)
                
                StepButton(label: "+", enabled: value < range.upperBound, action: onIncrement)
            }
        }
        .padding(.horizontal, 14)
        .padding(.vertical, 10)
        .background(Color.colorSurface)
        .clipShape(RoundedRectangle(cornerRadius: 10))
    }
}

private struct StepButton: View {
    let label:   String
    let enabled: Bool
    let action:  () -> Void
    
    var body: some View {
        Button(action: action) {
            Text(label)
                .font(.system(size: 14))
                .foregroundColor(Color.colorTextSecondary)
                .frame(width: 26, height: 26)
                .background(Color(hex: "#222222"))
                .clipShape(RoundedRectangle(cornerRadius: 5))
        }
        .disabled(!enabled)
        .opacity(enabled ? 1 : 0.3)
    }
}

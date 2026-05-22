//
//  RulesetGrid.swift
//  iosApp
//
//  Created by Himanshu Sherkar on 22/05/26.
//

import SwiftUI
import SharedLogic

struct RulesetGrid: View {
    let selected: ChessRuleset
    let onSelect: (ChessRuleset) -> Void
    
    private let items: [(ChessRuleset, String, String)] = {
        let factories: [() -> ChessRuleset] = [
            ChessRuleset.Blitz.init,
            ChessRuleset.Quick.init,
            ChessRuleset.Action.init
        ]
        
        return factories.map { make in
            let rs = make()
            return (rs, "\(rs.name) chess", "\(rs.minutes)m")
        }
    }()
    
    var body: some View {
        HStack(spacing: 8) {
            ForEach(items, id: \.0) { ruleset, label, time in
                PresetCard(
                    type: label,
                    time: time,
                    isSelected: selected == ruleset,
                    onSelect: { onSelect(ruleset) }
                )
            }
        }
    }
}

private struct PresetCard: View {
    let type: String
    let time: String
    let isSelected: Bool
    let onSelect: () -> Void
    
    var borderColor: Color {
        withAnimation(.easeInOut(duration: 0.15)) {
            isSelected ? Color.colorAccentGreen : Color.colorBorder
        }
    }
    
    var bgColor: Color {
        withAnimation(.easeInOut(duration: 0.15)) {
            isSelected ? Color(hex: "#0D1F0D") : Color.colorSurface
        }
    }
    
    var body: some View {
        VStack(spacing: 4) {
            Text(type.uppercased())
                .font(.system(size: 9))
                .foregroundColor(Color.colorTextSecondary)
                .tracking(1.0)
            
            Text(time)
                .font(.system(size: 22, weight: .light))
                .foregroundColor(isSelected ? Color.colorAccentGreen : Color.colorTextPrimary)
        }
        .frame(maxWidth: .infinity)
        .padding(10)
        .background(bgColor)
        .clipShape(RoundedRectangle(cornerRadius: 10))
        .overlay(
            RoundedRectangle(cornerRadius: 10)
                .stroke(borderColor, lineWidth: 0.5)
        )
        .onTapGesture { onSelect() }
    }
}

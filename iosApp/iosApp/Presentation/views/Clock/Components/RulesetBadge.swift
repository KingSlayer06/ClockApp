//
//  RulesetBadge.swift
//  iosApp
//
//  Created by Himanshu Sherkar on 22/05/26.
//

import SwiftUI
import SharedLogic

struct RulesetBadge: View {
    let ruleset: ChessRuleset
    
    private var content: (icon: String, label: String) {
        switch ruleset {
            case .Action(): return ("bolt.fill", "Action")
            case .Quick(): return ("timer", "Quick")
            case .Blitz(): return ("bolt.fill", "Blitz")
            default: return ("clock.fill", "Custom")
        }
    }
    
    var body: some View {
        HStack(spacing: 4) {
            Image(systemName: content.icon)
                .resizable()
                .scaledToFit()
                .frame(width: 15, height: 15)
                .foregroundStyle(Color.textSecondary)
                .padding(.vertical, 4)
                .padding(.leading, 12)
            
            Text(content.label)
                .font(.system(size: 10))
                .foregroundColor(Color(hex: "#9090CC"))
                .tracking(1.0)
                .padding(.vertical, 4)
                .padding(.trailing, 12)
        }
        .background(Color(hex: "#1E1E3A"))
        .clipShape(RoundedRectangle(cornerRadius: 20))
        .padding(.top, 8)
    }
}

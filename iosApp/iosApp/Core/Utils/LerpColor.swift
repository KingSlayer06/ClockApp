//
//  LerpColor.swift
//  iosApp
//
//  Created by Himanshu Sherkar on 23/05/26.
//

import SwiftUI

extension Color {
    static func lerp(from: Color, to: Color, fraction: Double) -> Color {
        let f = CGFloat(min(max(0, fraction), 1))
        
        let fromUI = UIColor(from)
        let toUI = UIColor(to)
        
        var r1: CGFloat = 0, g1: CGFloat = 0, b1: CGFloat = 0, a1: CGFloat = 0
        var r2: CGFloat = 0, g2: CGFloat = 0, b2: CGFloat = 0, a2: CGFloat = 0
        
        guard fromUI.getRed(&r1, green: &g1, blue: &b1, alpha: &a1),
              toUI.getRed(&r2, green: &g2, blue: &b2, alpha: &a2) else {
            return from
        }
        
        return Color(
            red: r1 + (r2 - r1) * f,
            green: g1 + (g2 - g1) * f,
            blue: b1 + (b2 - b1) * f,
            opacity: a1 + (a2 - a1) * f
        )
    }
}

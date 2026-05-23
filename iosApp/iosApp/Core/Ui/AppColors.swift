//
//  AppColors.swift
//  iosApp
//
//  Created by Himanshu Sherkar on 21/05/26.
//

import Foundation
import SwiftUI

public extension Color {
    static let background = Color(hex: "#0D0D0D")
    static let surface = Color(hex: "#161616")
    static let border = Color(hex: "#2A2A2A")
    static let inactive = Color(hex: "#111111")
    static let activeTint = Color(hex: "#1A1A2E")
    
    static let textPrimary = Color(hex: "#FF0F0F0")
    static let textSecondary = Color(hex: "#F888888")
    static let textHint = Color(hex: "#F555555")
    
    static let accentGreen = Color(hex: "#4ADE80")
    static let warning = Color(hex: "#EF9F27")
    static let danger = Color(hex: "#E24B4A")
    static let active = Color(hex: "#4ADE80")
}

public extension Color {
    init(hex: String) {
        let scanner = Scanner(string: hex)
        _ = scanner.scanString("#")
        
        var rgb: UInt64 = 0
        scanner.scanHexInt64(&rgb)
        
        let red = Double((rgb >> 16) & 0xFF) / 255.0
        let green = Double((rgb >> 8) & 0xFF) / 255.0
        let blue = Double(rgb & 0xFF) / 255.0
        
        self.init(red: red, green: green, blue: blue)
    }
}

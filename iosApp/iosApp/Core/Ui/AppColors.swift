//
//  AppColors.swift
//  iosApp
//
//  Created by Himanshu Sherkar on 21/05/26.
//

import Foundation
import SwiftUI

public extension Color {
    static let colorBackground   = Color(hex: "#0D0D0D")
    static let colorSurface      = Color(hex: "#161616")
    static let colorBorder       = Color(hex: "#2A2A2A")
    static let colorInactive     = Color(hex: "#111111")
    static let colorActiveTint   = Color(hex: "#1A1A2E")
    
    static let colorTextPrimary   = Color(hex: "#FF0F0F0")
    static let colorTextSecondary = Color(hex: "#F888888")
    static let colorTextHint      = Color(hex: "#F555555")
    
    static let colorAccentGreen  = Color(hex: "#4ADE80")
    static let colorWarning      = Color(hex: "#EF9F27")
    static let colorDanger       = Color(hex: "#E24B4A")
    static let colorActive       = Color(hex: "#4ADE80")
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

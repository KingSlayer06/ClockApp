//
//  Destination.swift
//  iosApp
//
//  Created by Himanshu Sherkar on 21/05/26.
//

import Foundation
import SharedLogic

public enum Destination: Hashable {
    case settings
    case clock(ruleset: ChessRuleset)
}

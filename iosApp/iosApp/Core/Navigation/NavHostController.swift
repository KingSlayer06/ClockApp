//
//  NavHostController.swift
//  iosApp
//
//  Created by Himanshu Sherkar on 21/05/26.
//

import SwiftUI

public final class NavHostController: ObservableObject {
    @Published public var path = NavigationPath()
    
    public init() { }
    
    public func navigate(route: Destination) {
        path.append(route)
    }
    
    public func popBackStack() {
        if path.isEmpty { return }
        
        path.removeLast()
    }
}

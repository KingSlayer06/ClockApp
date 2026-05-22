import SwiftUI
import SharedLogic

@main
struct iOSApp: App {
    init() {
        KoinKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            RootView()
        }
    }
}

struct RootView: View {
    @StateObject private var navHostController = NavHostController()
    
    var body: some View {
        NavigationStack(path: $navHostController.path) {
            SettingsView()
                .navigationDestination(for: Destination.self) { route in
                    route.setPath(navHostController)
                }
        }
    }
}

extension Destination {
    @ViewBuilder
    func setPath(_ navHostController: NavHostController) -> some View {
        switch self {
            case .settings: SettingsView()
                
            case .clock(let ruleset): ClockView(ruleset: ruleset) {
                navHostController.popBackStack()
            }
        }
    }
}

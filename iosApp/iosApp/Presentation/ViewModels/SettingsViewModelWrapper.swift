//
//  SettingsViewModelWrapper.swift
//  iosApp
//
//  Created by Himanshu Sherkar on 22/05/26.
//

import Foundation
import SharedLogic

@MainActor
final class SettingsViewModelWrapper: ObservableObject {
    private let viewModel: SettingsViewModel
    private var task: Task<Void, Never>?
    
    @Published private(set) var uiState: SettingsUiState
    
    init(
        viewModel: SettingsViewModel = ViewModelProvider().provideSettingsViewModel()
    ) {
        self.viewModel = viewModel
        self.uiState = viewModel.uiState.value
        
        // Collect StateFlow from Kotlin
        self.task = Task {
            for await uiState in viewModel.uiState {
                self.uiState = uiState
            }
        }
    }
    
    deinit {
        task?.cancel()
    }

    func selectRuleset(ruleset: ChessRuleset) {
        viewModel.selectRuleset(ruleset: ruleset)
    }

    func incrementMinutes() {
        viewModel.incrementMinutes()
    }

    func decrementMinutes() {
        viewModel.decrementMinutes()
    }

    func incrementIncrement() {
        viewModel.incrementIncrement()
    }

    func decrementIncrement() {
        viewModel.decrementIncrement()
    }
}

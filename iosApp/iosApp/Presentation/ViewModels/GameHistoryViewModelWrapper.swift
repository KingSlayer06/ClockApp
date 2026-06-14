//
//  GameHistoryViewModelWrapper.swift
//  iosApp
//
//  Created by Himanshu Sherkar on 14/06/26.
//

import Foundation
import SharedLogic

@MainActor
final class GameHistoryViewModelWrapper: ObservableObject {
    private let viewModel: GameHistoryViewModel
    private var task: Task<Void, Never>?
    
    @Published private(set) var uiState: GameHistoryUiState
    
    init(
        viewModel: GameHistoryViewModel = ViewModelProvider().provideGameHistoryViewModel()
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
    
    func getAll() {
        viewModel.getAll()
    }
    
    func deleteById(_ id: Int64) {
        viewModel.deleteGame(id: id)
    }
}

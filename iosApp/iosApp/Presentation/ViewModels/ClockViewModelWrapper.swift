//
//  ClockViewModelWrapper.swift
//  iosApp
//
//  Created by Himanshu Sherkar on 21/05/26.
//

import Foundation
import SharedLogic

@MainActor
final class ClockViewModelWrapper: ObservableObject {
    private let viewModel: ClockViewModel
    private var task: Task<Void, Never>?
    
    @Published private(set) var uiState: ClockUiState
    
    init(
        viewModel: ClockViewModel = ViewModelProvider().provideClockViewModel()
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
}

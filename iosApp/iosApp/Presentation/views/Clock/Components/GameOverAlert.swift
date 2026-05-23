//
//  GameOverAlert.swift
//  iosApp
//
//  Created by Himanshu Sherkar on 23/05/26.
//

import SwiftUI
import SharedLogic

struct GameOverAlert: View {
    let winner: Player?
    let onDismiss: () -> Void
    
    var winnerLabel: String {
        return winner == .one ? "Player 1" : "Player 2"
    }
    
    var body: some View {
        ZStack{}
            .alert("Time's Up!", isPresented: .constant(true)) {
                Button("Ok", role: .cancel, action: onDismiss)
            } message: {
                Text("\(winnerLabel) won the game!")
            }
    }
}

#Preview {
    GameOverAlert(
        winner: .one,
        onDismiss: {}
    )
}

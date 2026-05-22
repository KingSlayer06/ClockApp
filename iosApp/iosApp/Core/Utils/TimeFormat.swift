//
// Created by Himanshu Sherkar on 22/05/26.
//

import Foundation

private func formatTime(ms: Int64) -> String {
    let totalSeconds = max(0, ms / 1000)
    let minutes = totalSeconds / 60
    let seconds = totalSeconds % 60
    return String(format: "%02d:%02d", minutes, seconds)
}
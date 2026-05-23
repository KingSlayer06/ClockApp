package com.kingslayer06.clockapp.presentation.views.clock.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.kingslayer06.clockapp.domain.models.Player

@Composable
fun GameOverAlert(
    winner: Player?,
    onDismiss: () -> Unit
) {
    val winnerLabel = if (winner == Player.ONE) "Player 1" else "Player 2"

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Time's Up!") },
        text = { Text(text = "$winnerLabel won the game!") },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Ok")
            }
        }
    )
}
package com.example.weather_app.ui.screen.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

/**
 * ErrorView
 * エラーメッセージを画面中央に表示する
 */

@Composable
fun ErrorView(message: String){
    Text(
        text = message,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center
    )
}
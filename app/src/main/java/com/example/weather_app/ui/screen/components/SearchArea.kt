package com.example.weather_app.ui.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

/**
 * SearchArea
 * 都市名 TextField と検索ボタンを表示する
 * 通信中はボタンを無効化する
 */

@Composable
fun SearchArea(
    query: String,
    isLoading: Boolean,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            label = { Text("都市名 (例: Tokyo") },
            modifier = Modifier.weight(1f),
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = { onSearchClick() }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            )
        )
        Button(
            onClick = onSearchClick,
            enabled = !isLoading && query.isNotBlank()
        ) {
            Text("検索")
        }
    }
}
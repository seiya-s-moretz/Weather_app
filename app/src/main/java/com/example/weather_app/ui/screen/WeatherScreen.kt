package com.example.weather_app.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weather_app.ui.screen.components.ErrorView
import com.example.weather_app.ui.screen.components.SearchArea
import com.example.weather_app.ui.screen.components.WeatherCard
import com.example.weather_app.ui.viewmodel.WeatherUiState

/**
 * WeatherScreen
 * 画面全体のUIを描画する
 * 状態に応じてローディング・天気情報・エラーを出し分ける
 * 状態管理は一切行わない
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    uiState: WeatherUiState,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("お天気アプリ") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            SearchArea(
                query = uiState.query,
                isLoading = uiState.isLoading,
                onQueryChange = onQueryChange,
                onSearchClick = onSearchClick
            )
            Spacer(modifier = Modifier.height(16.dp))
            when {
                uiState.isLoading -> CircularProgressIndicator()
                uiState.weather != null -> WeatherCard(weather = uiState.weather)
                uiState.errorMessage != null -> ErrorView(message = uiState.errorMessage)
            }
        }
    }
}
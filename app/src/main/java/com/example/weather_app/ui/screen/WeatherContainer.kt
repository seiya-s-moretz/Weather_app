package com.example.weather_app.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather_app.ui.data.remote.RetrofitClient
import com.example.weather_app.ui.repository.WeatherRepository
import com.example.weather_app.ui.viewmodel.WeatherViewModel
import retrofit2.Retrofit

/**
 * WeatherContainer
 * `WeatherViewModel` を取得する
 * `uiState` を collectAsState() で購読する
 * ViewModel のイベントを WeatherScreen に橋渡しする
 */

@Composable
fun WeatherContainer() {
    val context = LocalContext.current
    val repository = WeatherRepository(RetrofitClient.apiService)
    val viewModel: WeatherViewModel = viewModel(
        factory = WeatherViewModel.provideFactory(repository)
    )

    val uiState by viewModel.uiState.collectAsState()

    WeatherScreen(
        uiState = uiState,
        onQueryChange = viewModel::updateQuery,
        onSearchClick = viewModel::fetchWeather
    )
}
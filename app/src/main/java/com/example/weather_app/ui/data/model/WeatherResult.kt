package com.example.weather_app.ui.data.model

/**
 * WeatherResult（sealed class）
 * API通信の結果を型安全に表現する
 * ViewModel が when で分岐できるようにする
 */

sealed class WeatherResult{
    data class Success(val data: WeatherUiModel) : WeatherResult()
    data class Error(val message: String) : WeatherResult()
}
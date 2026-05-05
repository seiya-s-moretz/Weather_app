package com.example.weather_app.ui.data.model

/**
 * WeatherUiModel（UI表示用モデル）
 * UI層で天気情報を表示するためのデータクラス
 * `WeatherResponse`（APIレスポンス）とUIを切り離すための中間モデル
 * APIの変更があっても `toUiModel()` だけ修正すればUIに影響しない
 */

data class WeatherUiModel(
    val cityName:String,
    val description: String,
    val iconUrl: String,
    val temperature: Double,
    val feelsLike: Double,
    val humidity: Int,
    val windSpeed: Double,
)
package com.example.weather_app.ui.data.model

import com.google.gson.annotations.SerializedName

/**
 * WeatherResponse（APIレスポンスモデル）
 * API レスポンスの JSON を Gson でパースするデータクラス
 * `toUiModel()` 拡張関数で WeatherUiModel に変換する
 */

data class WeatherResponse(
    val name: String,
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
)

data class Weather(
    val description: String,
    val icon: String,
)

data class Main(
    val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    val humidity: Int,
)

data class Wind(
    val speed: Double
)

// WeatherResponse → WeatherUiModel への変換
fun WeatherResponse.toUiModel(): WeatherUiModel {
    return WeatherUiModel (
        cityName = name,
        description = weather.firstOrNull()?.description ?: "",
        iconUrl = "https://openweathermap.org/img/wn/${weather.firstOrNull()?.icon}@2x.png",
        temperature = main.temp,
        feelsLike = main.feelsLike,
        humidity = main.humidity,
        windSpeed = wind.speed
    )
}
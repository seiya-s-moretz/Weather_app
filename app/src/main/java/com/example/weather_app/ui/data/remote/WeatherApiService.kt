package com.example.weather_app.ui.data.remote

import com.example.weather_app.ui.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * WeatherApiService
 * Retrofit の API インターフェースを定義する
 * HTTP GET リクエストを定義する
 */

interface WeatherApiService {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String,
        @Query("lang") lang: String
    ): WeatherResponse
}
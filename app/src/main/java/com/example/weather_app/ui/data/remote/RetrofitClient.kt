package com.example.weather_app.ui.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * RetrofitClient
 * Retrofit のシングルトンインスタンスを提供する
 * タイムアウト設定を行う
 */

object RetrofitClient{

    private const val BASE_URL = "https://api.openweathermap.org/"

    private val okHTTPClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    private  val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHTTPClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: WeatherApiService = retrofit.create(WeatherApiService::class.java)
}
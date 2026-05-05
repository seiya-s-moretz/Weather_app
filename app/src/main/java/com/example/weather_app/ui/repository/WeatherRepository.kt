package com.example.weather_app.ui.repository

import com.example.weather_app.BuildConfig
import com.example.weather_app.ui.data.model.WeatherResult
import com.example.weather_app.ui.data.model.toUiModel
import com.example.weather_app.ui.data.remote.WeatherApiService
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException


/**
 * WeatherRepository
 * WeatherApiService を抽象化し、ViewModel にデータ操作のインターフェースを提供する
 * API通信の成功・失敗を `WeatherResult` に変換して返す
 * 例外をキャッチしてエラーメッセージに変換する（ViewModel にエラー処理を漏らさない）
 */

class WeatherRepository(
    private val apiService: WeatherApiService
) {

    suspend fun getWeather(city: String): WeatherResult {
        return try {
            val response = apiService.getWeather(
                city = city,
                apiKey = BuildConfig.WEATHER_API_KEY,
                units = "metric",
                lang = "ja"
            )
            WeatherResult.Success(response.toUiModel())
        } catch (e: HttpException) {
            val message = when (e.code()) {
                404 -> "都市が見つかりませんでした"
                401 -> "APIキーが無効です"
                else -> "サーバエラーが発生しました"
            }
            WeatherResult.Error(message)
        } catch (e: IOException) {
            WeatherResult.Error("ネットワークに接続できません")
        } catch (e: SocketTimeoutException) {
            WeatherResult.Error("通信がタイムアウトしました")
        } catch (e: Exception) {
            WeatherResult.Error("エラーが発生しました")
        }
    }

}
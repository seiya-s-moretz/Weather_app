package com.example.weather_app.ui.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weather_app.ui.data.model.WeatherUiModel

/**
 * WeatherCard
 * 取得した天気情報をカード形式で表示する
 * 天気アイコンを Coil で非同期ロードする
 */

@Composable
fun WeatherCard(weather: WeatherUiModel){
    Card(
        modifier = Modifier.fillMaxWidth()
    ){
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = weather.cityName,
                style = MaterialTheme.typography.headlineMedium
            )
            AsyncImage(
                model = weather.iconUrl,
                contentDescription = weather.description,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = weather.description,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "気温 ${weather.temperature}℃" )
            Text(text = "体感温度 ${weather.feelsLike}℃" )
            Text(text = "湿度 ${weather.humidity}%" )
            Text(text = "風速 ${weather.windSpeed}m/s" )
        }
    }
}
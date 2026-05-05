package com.example.weather_app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weather_app.ui.data.model.WeatherResult
import com.example.weather_app.ui.data.model.WeatherUiModel
import com.example.weather_app.ui.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * WeatherViewModel
 * UiState を保持・更新する
 * Repository を呼び出して天気情報を取得する
 * エラーハンドリングを行い、エラー種別に応じたメッセージを UiState に反映する
 */

data class WeatherUiState(
    val query: String = "",
    val isLoading: Boolean = false,
    val weather: WeatherUiModel? = null,
    val errorMessage: String? = null
)

class WeatherViewModel(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState

    fun updateQuery(newQuery: String) {
        _uiState.update { it.copy(query = newQuery) }
    }

    fun fetchWeather() {
        val query = uiState.value.query
        if (query.isBlank()) return

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, weather = null, errorMessage = null)
            }

            when (val result = repository.getWeather(query)) {
                is WeatherResult.Success -> {
                    _uiState.update {
                        it.copy(isLoading = false, weather = result.data)
                    }
                }

                is WeatherResult.Error -> {
                    _uiState.update { it.copy(isLoading = false, errorMessage = result.message) }
                }
            }
        }
    }

    companion object{
        fun provideFactory(repository: WeatherRepository): ViewModelProvider.Factory{
            return object : ViewModelProvider.Factory{
                override fun <T: ViewModel> create(modelClass: Class<T>): T{
                    @Suppress("UNCHECKED_CAST")
                    return WeatherViewModel(repository) as T
                }
            }
        }
    }
}
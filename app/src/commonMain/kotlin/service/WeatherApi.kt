package service

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url

class WeatherApi() {
    companion object {
        private const val baseUrl = "https://api.openweathermap.org"
    }

    private val client = HttpClient()

    suspend fun fetchWeather(): String {
        return client.get<String> {
            url("$baseUrl/data/2.5/weather?q=Munich,de&appid=7d62ffa8d2060d8fce9a6b7b0b148377")
        }
    }
}
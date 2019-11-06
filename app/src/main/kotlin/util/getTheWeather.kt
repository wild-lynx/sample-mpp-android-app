package util

import io.WeatherApi
import io.citiesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

actual suspend fun getTheWeather(
    resultWeatherList: MutableList<WeatherApi.Weather>,
    weatherApi: WeatherApi,
    cList: MutableList<String>
) {
    if (cList.size == 0) {
        resultWeatherList.add(withContext(Dispatchers.IO) { weatherApi.fetchWeather() })
    } else {
        cList.forEach {
            resultWeatherList.add(withContext(Dispatchers.IO) { weatherApi.fetchWeather(it) })
        }
    }
}
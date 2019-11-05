package util

import io.WeatherApi
import io.citiesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun getTheWeather(
    resultWeatherList: MutableList<WeatherApi.Weather>,
    weatherApi: WeatherApi,
    cList: MutableList<String> = citiesList
) {
    citiesList.forEach {
        resultWeatherList.add(withContext(Dispatchers.IO) { weatherApi.fetchWeather(it) })
    }
}
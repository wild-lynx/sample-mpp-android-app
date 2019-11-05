package io

import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.engine.okhttp.OkHttpEngine
import io.ktor.util.InternalAPI
import kotlinx.coroutines.*
import kotlin.test.Test
import kotlin.test.assertTrue
import util.getTheWeather
import kotlin.coroutines.CoroutineContext
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlinx.coroutines.test.*

@InternalAPI // to allow usage of `OkHttpEngine` in `showTheWeather` fun
class WeatherApiTests: CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job

    val weatherApi = WeatherApi(OkHttpEngine(OkHttpConfig()))

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun getTheWeather_EmptyCityTest() {
        launch(Dispatchers.Main){
            val resultWeatherList = mutableListOf<WeatherApi.Weather>()
            getTheWeather(resultWeatherList, weatherApi, mutableListOf())
            println("Got the result: $resultWeatherList")
            assertTrue(resultWeatherList[0].name == "Mundich")
        }
    }

}
package io

import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.engine.okhttp.OkHttpEngine
import io.ktor.util.InternalAPI
import kotlinx.coroutines.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import util.getTheWeather
import kotlin.coroutines.CoroutineContext
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@InternalAPI // to allow usage of `OkHttpEngine` in `showTheWeather` fun
class WeatherApiTests {
    @UseExperimental(kotlinx.coroutines.ObsoleteCoroutinesApi::class)
    val weatherApi = WeatherApi(OkHttpEngine(OkHttpConfig()))

    // commented out due to the replacement of `runBlockingTest` with `runBlocking` — see the comment below
/*
    @UseExperimental(kotlinx.coroutines.ObsoleteCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @BeforeTest
    @UseExperimental(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @AfterTest
    @UseExperimental(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }*/

    @Test
    @ExperimentalCoroutinesApi
    fun getTheWeather_EmptyCityTest() = runBlocking {
        // using `runBlocking` instead of `runBlockingTest` because of https://github.com/Kotlin/kotlinx.coroutines/issues/1204
        val resultWeatherList =
            mutableListOf<WeatherApi.Weather>() // empty list, so the default default city ($defaultCity) value should be used
        val deferred = async {
            getTheWeather(resultWeatherList, weatherApi, mutableListOf())
            assertEquals(resultWeatherList[0].name, defaultCity)
        }
        deferred.await()
    }
}
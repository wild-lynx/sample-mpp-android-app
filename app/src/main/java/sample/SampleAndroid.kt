package sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import io.citiesList
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.engine.okhttp.OkHttpEngine
import io.ktor.util.InternalAPI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import service.WeatherApi
import service.getDeviceModel
import service.getFullDeviceInfo
import kotlin.coroutines.CoroutineContext

actual class Sample {
    actual fun checkMe() = 44
}

actual object Platform {
    actual val name: String = "Android"
}

class MainActivity : AppCompatActivity(), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job


    private fun initializeWeatherList() {
        weatherListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = WeatherListViewAdapter()
        }
    }

    inline fun <reified T> Any.safeCast() = this as? T

    @InternalAPI // to allow usage of `OkHttpEngine` below
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeWeatherList()

        /* Set the text with the device info */
        header.text = getDeviceModel()
        deviceInfo.text = getFullDeviceInfo()

        /* Download and show the weather info */
        val weatherApi = WeatherApi(OkHttpEngine(OkHttpConfig()))
        var resultStr: String = "Getting the weather..."

        launch(Dispatchers.Main) {
            try {
                val resultWeatherList = mutableListOf<WeatherApi.Weather>()
                citiesList.forEach {
                    resultWeatherList.add(withContext(Dispatchers.IO) { weatherApi.fetchWeather(it) })
                }
                //val result = withContext(Dispatchers.IO) { weatherApi.fetchWeather() }
/*                resultStr = result.toString()
                weatherView.text = resultStr*/
                weatherListView.adapter?.safeCast<WeatherListViewAdapter>()
                    ?.updWeatherList(resultWeatherList)
            } catch (e: Exception) {
                resultStr = e.message.toString()
                weatherView.text = resultStr
            }
        }

        job.complete()
    }
}
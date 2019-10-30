package sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

    @InternalAPI // to allow usage of `OkHttpEngine` below
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Set the text with the device info */
        header.text = getDeviceModel()
        deviceInfo.text = getFullDeviceInfo()

        /* Download and show the weather info in a toast */
        val weatherApi = WeatherApi(OkHttpEngine(OkHttpConfig()))
        launch(Dispatchers.Main) {
            try {
                val result = withContext(Dispatchers.IO) { weatherApi.fetchWeather() }
                Toast.makeText(this@MainActivity, result.toString(), Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
package com.lnpdit.sunnyweather.logic

import androidx.lifecycle.liveData
import com.lnpdit.sunnyweather.logic.model.PlaceResponse
import com.lnpdit.sunnyweather.logic.model.Weather
import com.lnpdit.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * Created by HePeng on 2023/7/11
 */
object Repository {
    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val response: PlaceResponse = SunnyWeatherNetwork.searchPlace(query)
        Result.success(response)
    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val realtimeDeffer = async {
                SunnyWeatherNetwork.getRealtimeWeather(lng, lat)
            }
            val dailyDeffer = async {
                SunnyWeatherNetwork.getDailyWeather(lng, lat)
            }
            val realtime = realtimeDeffer.await()
            val daily = dailyDeffer.await()
            if (realtime.status == "ok" && daily.status == "ok") {
                val weather = Weather(realtime, daily)
                Result.success(weather)
            } else {
                Result.failure(RuntimeException("Realtime Status Code is ${realtime.status} and Daily Status code is ${daily.status}"))
            }
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }
}
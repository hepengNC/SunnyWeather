package com.lnpdit.sunnyweather.logic

import androidx.lifecycle.liveData
import com.lnpdit.sunnyweather.logic.model.PlaceResponse
import com.lnpdit.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers

/**
 * Created by HePeng on 2023/7/11
 */
object Repository {
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val response:PlaceResponse = SunnyWeatherNetwork.searchPlace(query)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure<List<PlaceResponse.Place>>(e)
        }
        emit(result)
    }
}
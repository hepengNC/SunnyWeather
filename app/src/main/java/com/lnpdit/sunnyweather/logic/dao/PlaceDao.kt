package com.lnpdit.sunnyweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.lnpdit.sunnyweather.SunnyWeatherApplication
import com.lnpdit.sunnyweather.logic.model.PlaceResponse

/**
 * Created by HePeng on 2023/7/12
 */
object PlaceDao {
    private fun getSharedPreferences() =
        SunnyWeatherApplication.context.getSharedPreferences("weather", Context.MODE_PRIVATE)

    fun savePlace(place: PlaceResponse.Place) {
        getSharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getPlace(): PlaceResponse.Place {
        val place = getSharedPreferences().getString("place", "")
        return Gson().fromJson(place, PlaceResponse.Place::class.java)
    }

    fun isSavePlace():Boolean = getSharedPreferences().contains("place")
}
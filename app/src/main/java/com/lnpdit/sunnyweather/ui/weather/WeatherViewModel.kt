package com.lnpdit.sunnyweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lnpdit.sunnyweather.logic.Repository
import com.lnpdit.sunnyweather.logic.model.PlaceResponse
import kotlin.math.ln

/**
 * Created by HePeng on 2023/7/12
 */
class WeatherViewModel : ViewModel() {
    private val weatherLiveData = MutableLiveData<PlaceResponse.Place.Location>()
    var lng = ""
    var lat = ""
    var placeName = ""
    val weather = Transformations.switchMap(weatherLiveData) { location ->
        Repository.refreshWeather(location.lng.toString(), location.lat.toString())
    }

    fun refreshWeather(lng: String, lat: String) {
        weatherLiveData.value = PlaceResponse.Place.Location(lat.toDouble(), lng.toDouble())
    }
}
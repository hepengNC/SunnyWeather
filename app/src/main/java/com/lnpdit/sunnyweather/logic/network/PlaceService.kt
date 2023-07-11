package com.lnpdit.sunnyweather.logic.network

import com.lnpdit.sunnyweather.SunnyWeatherApplication
import com.lnpdit.sunnyweather.logic.model.PlaceResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by HePeng on 2023/7/11
 */
interface PlaceService {
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun getPlaces(@Query("query") query: String): Observable<PlaceResponse>
}
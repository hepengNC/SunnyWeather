package com.lnpdit.sunnyweather.logic.network

import com.lnpdit.sunnyweather.SunnyWeatherApplication
import com.lnpdit.sunnyweather.logic.model.DailyResponse
import com.lnpdit.sunnyweather.logic.model.RealtimeResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by HePeng on 2023/7/12
 */
interface WeatherService {
    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtime(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Observable<RealtimeResponse>

    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDaily(@Path("lng") lng: String, @Path("lat") lat: String): Observable<DailyResponse>
}
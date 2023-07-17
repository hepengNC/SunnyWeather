package com.lnpdit.sunnyweather.test.park.network

import com.lnpdit.sunnyweather.test.constant.UrlConstant
import com.lnpdit.sunnyweather.test.park.model.ParkResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Created by HePeng on 2023/7/13
 */
interface ParkService {
    @GET(UrlConstant.getParkList)
    fun getParkList(
        @Header("Authorization") authorization: String,
        @Query("managerId") managerId: String
    ): Observable<ParkResponse>
}
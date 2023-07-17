package com.lnpdit.sunnyweather.test.login.network

import com.lnpdit.sunnyweather.test.constant.UrlConstant
import com.lnpdit.sunnyweather.test.login.model.LoginInfoResponse
import com.lnpdit.sunnyweather.test.login.model.LoginRequest
import com.lnpdit.sunnyweather.test.login.model.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by HePeng on 2023/7/13
 */
interface LoginService {
    @POST(UrlConstant.login)
    fun login(@Body request: LoginRequest): Observable<LoginResponse>

    @GET(UrlConstant.getInfo)
    fun getInfo(@Header("Authorization") token: String): Observable<LoginInfoResponse>
}
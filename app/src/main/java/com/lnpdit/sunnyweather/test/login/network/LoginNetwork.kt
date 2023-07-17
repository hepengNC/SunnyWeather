package com.lnpdit.sunnyweather.test.login.network

import com.lnpdit.sunnyweather.test.http.ServiceCreator
import com.lnpdit.sunnyweather.test.login.model.LoginRequest
import com.lnpdit.sunnyweather.test.park.network.ParkService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Created by HePeng on 2023/7/13
 */
object LoginNetwork {
    private val loginService = ServiceCreator.create<LoginService>()
    suspend fun login(request: LoginRequest) = loginService.login(request).await()
    suspend fun getInfo(token: String) = loginService.getInfo(token).await()

    private val parkService = ServiceCreator.create<ParkService>()
    suspend fun getParkList(token: String, managerId: String) =
        parkService.getParkList(token, managerId).await()

    suspend fun <T> Observable<T>.await(): T {
        return suspendCoroutine { continuation ->
            subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    continuation.resume(response)
                }, { error ->
                    continuation.resumeWithException(error)
                })
        }
    }
}
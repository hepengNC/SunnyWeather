package com.lnpdit.sunnyweather.logic.network

import com.lnpdit.sunnyweather.logic.model.PlaceResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Created by HePeng on 2023/7/11
 */
object SunnyWeatherNetwork {
    private val placeService = ServiceCreator.create<PlaceService>()
    suspend fun searchPlace(query: String) = placeService.getPlaces(query).await()
    private suspend fun <T> Observable<T>.await(): T {
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
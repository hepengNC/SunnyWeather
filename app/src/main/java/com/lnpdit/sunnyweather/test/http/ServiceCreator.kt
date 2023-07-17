package com.lnpdit.sunnyweather.test.http

import com.lnpdit.sunnyweather.test.constant.UrlConstant
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by HePeng on 2023/7/13
 * Retrofit构造器
 */
object ServiceCreator {
    private val retrofit = Retrofit.Builder()
        .baseUrl(UrlConstant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
    inline fun <reified T> create(): T = create(T::class.java)
}
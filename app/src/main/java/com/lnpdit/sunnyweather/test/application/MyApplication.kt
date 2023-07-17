package com.lnpdit.sunnyweather.test.application

import android.app.Application
import android.content.Context

/**
 * Created by HePeng on 2023/7/13
 */
class MyApplication : Application() {
    companion object {
        lateinit var context: Context
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext
    }

    fun getToken() = getSharedPreferences("login", Context.MODE_PRIVATE).getString("token", "")
    fun getManagerId() =
        getSharedPreferences("login", Context.MODE_PRIVATE).getString("managerId", "")
}
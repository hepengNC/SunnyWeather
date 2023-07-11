package com.lnpdit.sunnyweather

import android.app.Application
import android.content.Context

/**
 * Created by HePeng on 2023/7/11
 */
class SunnyWeatherApplication:Application() {
    companion object{
        const val TOKEN = "uR9kNiY7H63rPZxb"
        lateinit var context: Context
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
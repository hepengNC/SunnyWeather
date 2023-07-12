package com.lnpdit.sunnyweather.logic.model

import com.lnpdit.sunnyweather.R

/**
 * Created by HePeng on 2023/7/12
 */
class Sky(val info: String, val icon: Int, val bg: Int)

private val sky = mapOf(
    "CLEAR_DAY" to Sky("晴", R.drawable.clear, R.drawable.clear_bg),
    "CLEAR_NIGHT" to Sky("晴", R.drawable.clear, R.drawable.clear_bg),
    "PARTLY_CLOUDY_DAY" to Sky(
        "多云", R.drawable.cloudy,
        R.drawable.cloudy_bg
    ),
    "PARTLY_CLOUDY_NIGHT" to Sky(
        "多云", R.drawable.cloudy,
        R.drawable.cloudy_bg
    ),
    "CLOUDY" to Sky("阴", R.drawable.cloudy, R.drawable.cloudy_bg),
    "WIND" to Sky("大风", R.drawable.cloudy, R.drawable.cloudy_bg),
    "LIGHT_RAIN" to Sky("小雨", R.drawable.rain, R.drawable.rain_bg),
    "MODERATE_RAIN" to Sky("中雨", R.drawable.rain, R.drawable.rain_bg),
    "HEAVY_RAIN" to Sky("大雨", R.drawable.rain, R.drawable.rain_bg),
    "STORM_RAIN" to Sky("暴雨", R.drawable.rain, R.drawable.rain_bg),
    "THUNDER_SHOWER" to Sky("雷阵雨", R.drawable.rain, R.drawable.rain_bg),
    "SLEET" to Sky("雨夹雪", R.drawable.rain, R.drawable.rain_bg),
    "LIGHT_SNOW" to Sky("小雪", R.drawable.rain, R.drawable.rain_bg),
    "MODERATE_SNOW" to Sky("中雪", R.drawable.rain, R.drawable.rain_bg),
    "HEAVY_SNOW" to Sky("大雪", R.drawable.rain, R.drawable.rain_bg),
    "STORM_SNOW" to Sky("暴雪", R.drawable.rain, R.drawable.rain_bg),
    "HAIL" to Sky("冰雹", R.drawable.rain, R.drawable.rain_bg),
    "LIGHT_HAZE" to Sky("轻度雾霾", R.drawable.rain, R.drawable.rain_bg),
    "MODERATE_HAZE" to Sky("中度雾霾", R.drawable.rain, R.drawable.rain_bg),
    "HEAVY_HAZE" to Sky("重度雾霾", R.drawable.rain, R.drawable.rain_bg),
    "FOG" to Sky("雾", R.drawable.rain, R.drawable.rain_bg),
    "DUST" to Sky("浮尘", R.drawable.rain, R.drawable.rain_bg)
)

fun getSky(skyStr: String): Sky {
    return (sky[skyStr] ?: sky["CLEAR_DAY"])!!
}
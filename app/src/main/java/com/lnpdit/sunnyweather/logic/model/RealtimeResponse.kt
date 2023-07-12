package com.lnpdit.sunnyweather.logic.model

/**
 * Created by HePeng on 2023/7/12
 */
data class RealtimeResponse(
    val status: String,
    val api_version: String,
    val api_status: String,
    val lang: String,
    val unit: String,
    val tzshift: Int,
    val timezone: String,
    val server_time: Int,
    val location: List<Double>,
    val result: Result
) {
    data class Result(
        val realtime: Realtime,
        val primary: Int
    ) {
        data class Realtime(
            val status: String,
            val temperature: Int,
            val humidity: Double,
            val cloudrate: Double,
            val skycon: String,
            val visibility: Double,
            val dswrf: Double,
            val wind: Wind,
            val pressure: Double,
            val apparent_temperature: Double,
            val precipitation: Precipitation,
            val air_quality: AirQuality,
            val life_index: LifeIndex
        ) {
            data class Wind(
                val speed: Double,
                val direction: Double
            )

            data class Precipitation(
                val local: Local,
                val nearest: Nearest
            ) {
                data class Local(
                    val status: String,
                    val datasource: String,
                    val intensity: Double
                )

                data class Nearest(
                    val status: String,
                    val distance: Double,
                    val intensity: Double
                )
            }

            data class AirQuality(
                val pm25: Int,
                val pm10: Int,
                val o3: Int,
                val so2: Int,
                val no2: Int,
                val co: Double,
                val aqi: Aqi,
                val description: Description
            ) {
                data class Aqi(
                    val chn: Int,
                    val usa: Int
                )

                data class Description(
                    val chn: String,
                    val usa: String
                )
            }

            data class LifeIndex(
                val ultraviolet: Ultraviolet,
                val comfort: Comfort
            ) {
                data class Ultraviolet(
                    val index: Int,
                    val desc: String
                )

                data class Comfort(
                    val index: Int,
                    val desc: String
                )
            }
        }
    }
}
package com.lnpdit.sunnyweather.logic.model

/**
 * Created by HePeng on 2023/7/12
 */
data class DailyResponse(
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
        val daily: Daily,
        val primary: Int
    ) {
        data class Daily(
            val status: String,
            val astro: List<Astro>,
            val precipitation: List<Precipitation>,
            val temperature: List<Temperature>,
            val wind: List<Wind>,
            val humidity: List<Humidity>,
            val cloudrate: List<Cloudrate>,
            val pressure: List<Pressure>,
            val visibility: List<Visibility>,
            val dswrf: List<Dswrf>,
            val air_quality: AirQuality,
            val skycon: List<Skycon>,
            val skycon_08h_20h: List<Skycon08h20h>,
            val skycon_20h_32h: List<Skycon20h32h>,
            val life_index: LifeIndex
        ) {
            data class Astro(
                val date: String,
                val sunrise: Sunrise,
                val sunset: Sunset
            ) {
                data class Sunrise(
                    val time: String
                )

                data class Sunset(
                    val time: String
                )
            }

            data class Precipitation(
                val date: String,
                val max: Double,
                val min: Double,
                val avg: Double
            )

            data class Temperature(
                val date: String,
                val max: Double,
                val min: Double,
                val avg: Double
            )

            data class Wind(
                val date: String,
                val max: Max,
                val min: Min,
                val avg: Avg
            ) {
                data class Max(
                    val speed: Double,
                    val direction: Double
                )

                data class Min(
                    val speed: Double,
                    val direction: Double
                )

                data class Avg(
                    val speed: Double,
                    val direction: Double
                )
            }

            data class Humidity(
                val date: String,
                val max: Double,
                val min: Double,
                val avg: Double
            )

            data class Cloudrate(
                val date: String,
                val max: Double,
                val min: Double,
                val avg: Double
            )

            data class Pressure(
                val date: String,
                val max: Double,
                val min: Double,
                val avg: Double
            )

            data class Visibility(
                val date: String,
                val max: Double,
                val min: Double,
                val avg: Double
            )

            data class Dswrf(
                val date: String,
                val max: Double,
                val min: Int,
                val avg: Double
            )

            data class AirQuality(
                val aqi: List<Aqi>,
                val pm25: List<Pm25>
            ) {
                data class Aqi(
                    val date: String,
                    val max: Max,
                    val avg: Avg,
                    val min: Min
                ) {
                    data class Max(
                        val chn: Int,
                        val usa: Int
                    )

                    data class Avg(
                        val chn: Int,
                        val usa: Int
                    )

                    data class Min(
                        val chn: Int,
                        val usa: Int
                    )
                }

                data class Pm25(
                    val date: String,
                    val max: Int,
                    val avg: Int,
                    val min: Int
                )
            }

            data class Skycon(
                val date: String,
                val value: String
            )

            data class Skycon08h20h(
                val date: String,
                val value: String
            )

            data class Skycon20h32h(
                val date: String,
                val value: String
            )

            data class LifeIndex(
                val ultraviolet: List<Ultraviolet>,
                val carWashing: List<CarWashing>,
                val dressing: List<Dressing>,
                val comfort: List<Comfort>,
                val coldRisk: List<ColdRisk>
            ) {
                data class Ultraviolet(
                    val date: String,
                    val index: String,
                    val desc: String
                )

                data class CarWashing(
                    val date: String,
                    val index: String,
                    val desc: String
                )

                data class Dressing(
                    val date: String,
                    val index: String,
                    val desc: String
                )

                data class Comfort(
                    val date: String,
                    val index: String,
                    val desc: String
                )

                data class ColdRisk(
                    val date: String,
                    val index: String,
                    val desc: String
                )
            }
        }
    }
}
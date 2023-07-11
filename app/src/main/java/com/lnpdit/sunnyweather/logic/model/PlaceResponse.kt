package com.lnpdit.sunnyweather.logic.model

/**
 * Created by HePeng on 2023/7/11
 */
data class PlaceResponse(
    val status: String,
    val query: String,
    val places: List<Place>
) {
    data class Place(
        val id: String,
        val name: String,
        val formatted_address: String,
        val location: Location,
        val place_id: String
    ) {
        data class Location(
            val lat: Double,
            val lng: Double
        )
    }
}
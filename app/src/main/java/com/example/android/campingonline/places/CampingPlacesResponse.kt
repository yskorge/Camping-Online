package com.example.android.campingonline.places

import com.google.android.gms.maps.model.LatLng

data class CampingPlacesResponse(
    val geometry: Geometry,
    val name: String,
    val address: String,
    val rating: Float,
    val id: String
) {

    data class Geometry(
        val location: GeometryLocation
    )

    data class GeometryLocation(
        val lat: Double,
        val lng: Double
    )
}

fun CampingPlacesResponse.toCampingPlace(): CampingPlace = CampingPlace(
    name = name,
    latLng = LatLng(geometry.location.lat, geometry.location.lng),
    address = address,
    rating = rating,
    id = id
)
package com.example.android.campingonline.places

import com.google.android.gms.maps.model.LatLng

data class CampingPlace(
    val name: String,
    val latLng: LatLng,
    val address: String,
    val rating: Float,
    val id: String
)
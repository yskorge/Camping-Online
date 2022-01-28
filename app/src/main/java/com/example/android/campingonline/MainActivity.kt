package com.example.android.campingonline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.android.campingonline.adapters.MarkerInfoWindowAdapter
import com.example.android.campingonline.places.CampingPlace
import com.example.android.campingonline.places.PlacesReader
import com.example.android.campingonline.utils.BitmapHelper
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity() {

    private val campingPlaces: List<CampingPlace> by lazy {
        PlacesReader(this).read()
    }

    // TODO use tent icon
    private val houseIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(this, R.color.purple_700)
        BitmapHelper.vectorToBitmap(this, R.drawable.ic_baseline_house_24, color)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment

        mapFragment?.getMapAsync { googleMap ->

            addMarkers(googleMap)

//            // Ensure all places are visible in the map.
            googleMap.setOnMapLoadedCallback {
                val bounds = LatLngBounds.builder()
                campingPlaces.forEach { bounds.include(it.latLng) }
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 20))
            }

            // Set custom info window adapter
            googleMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(this))
        }
    }


    /**
     * Adds marker representations of the places list on the provided GoogleMap object
     */
    private fun addMarkers(googleMap: GoogleMap) {
        campingPlaces.forEach { campingPlace ->
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(campingPlace.name)
                    .position(campingPlace.latLng)
                    .icon(houseIcon)
            )

            // Set camping place as the tag on the marker object so it can be referenced within
            // MarkerInfoWindowAdapter
            marker.tag = campingPlace
        }
    }
}
package com.example.android.campingonline.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.android.campingonline.R
import com.example.android.campingonline.places.CampingPlace
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

/**
 * This Adapter that extends the InfoWindow interface contains two methods, getInfoWindow() and getInfoContents().
 * Both methods return an optional View object wherein the former is used to customize the window itself,
 * while the latter is to customize its contents. In our case, we implement both and customize the return
 * of getInfoContents() while returning null in getInfoWindow(), which indicates that the default window should be used.
 */
class MarkerInfoWindowAdapter(
    private val context: Context
) : GoogleMap.InfoWindowAdapter {

    override fun getInfoContents(marker: Marker?): View? {
        // 1. Get tag
        val place = marker?.tag as? CampingPlace ?: return null

        // 2. Inflate view and set title, address, and rating
        val view = LayoutInflater.from(context).inflate(
            R.layout.marker_info_contents, null
        )

        view.findViewById<TextView>(R.id.text_view_title).text = place.name
        view.findViewById<TextView>(R.id.text_view_address).text = place.address
        view.findViewById<TextView>(R.id.text_view_rating).text = "Rating: %.2f".format(place.rating)

        return view
    }

    override fun getInfoWindow(marker: Marker?): View? {
        // Return null to indicate that the
        // default window (white bubble) should be used
        return null
    }
}
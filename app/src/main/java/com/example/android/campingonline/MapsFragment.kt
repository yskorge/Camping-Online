package com.example.android.campingonline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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

/**
 * A simple [Fragment] subclass.
 * Use the [MapsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapsFragment : Fragment() {

    private val campingPlaces: List<CampingPlace> by lazy {
        PlacesReader(requireContext()).read()
    }

    private val houseIcon: BitmapDescriptor by lazy {
        BitmapHelper.vectorToBitmap(requireContext(), R.drawable.ic_tent_camping_red, null)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val routeView = inflater.inflate(R.layout.fragment_maps, container, false)

        val mapFragment = childFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment

        mapFragment?.getMapAsync { googleMap ->

            addMarkers(googleMap)

            // Ensure all places are visible in the map.
            googleMap.setOnMapLoadedCallback {
                val bounds = LatLngBounds.builder()
                campingPlaces.forEach { bounds.include(it.latLng) }
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 20))
            }

            // Set custom info window adapter
            googleMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(requireContext()))

            // TODO add transition animation && use button in the marker...big job :-(
            googleMap.setOnInfoWindowClickListener {
//                findNavController().navigate(R.id.campingDetailsFragment_dest)

                val campingPlace = it.tag as? CampingPlace
                val campingSpotId = campingPlace?.id

                if (campingSpotId != null) {
                    val action = MapsFragmentDirections.actionMapsFragmentToCampingDetailsFragment(campingSpotId)
                    findNavController().navigate(action)
                }
            }
        }

        // Inflate the layout for this fragment
        return routeView
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MapsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapsFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}
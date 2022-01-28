package com.example.android.campingonline.places

import android.content.Context
import com.example.android.campingonline.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream
import java.io.InputStreamReader



/**
 * Reads a list of place JSON objects from the file camping_places.json
 */
class PlacesReader(private val context: Context) {

    // GSON object responsible for converting from JSON to a Place object
    private val gson = Gson()

    // InputStream representing places.json
    private val inputStream: InputStream
        get() = context.resources.openRawResource(R.raw.camping_places)

    /**
     * Reads the list of places JSON objects in the file camping_places.json
     * and returns a list of CampingPlace objects
     */
    fun read(): List<CampingPlace> {
        val itemType = object : TypeToken<List<CampingPlacesResponse>>() {}.type
        val reader = InputStreamReader(inputStream)
        return gson.fromJson<List<CampingPlacesResponse>>(reader, itemType).map {
            it.toCampingPlace()
        }
    }
}
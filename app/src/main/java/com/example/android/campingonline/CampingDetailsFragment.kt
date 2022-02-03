package com.example.android.campingonline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

class CampingDetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}

        val safeArgs: CampingDetailsFragmentArgs by navArgs()
        val campingSpotId = safeArgs.campingSpotId
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camping_details, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CampingDetailsFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}
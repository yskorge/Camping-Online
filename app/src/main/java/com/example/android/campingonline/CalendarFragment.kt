package com.example.android.campingonline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.fragment.findNavController
import com.example.android.campingonline.ui.theme.BasicsCampingOnlineTheme

class CalendarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                BasicsCampingOnlineTheme {
                    CalenderScreen()
                }
            }
        }

    }

    @Composable
    fun CalenderScreen() {
        Column {
            // TODO title of calender is black in dark mode
            SelectableCalendar()
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { findNavController().navigate(R.id.confirmationFragment_dest) }) {
                    Text("Select Dates")
                }
            }
        }
    }

    @Preview
    @Composable
    fun PreviewCalender() {
        CalenderScreen()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalendarFragment()
    }
}
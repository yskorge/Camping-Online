package com.example.android.campingonline.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.fragment.findNavController
import com.example.android.campingonline.R
import com.example.android.campingonline.ui.theme.BasicsCampingOnlineTheme

class ConfirmationDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                BasicsCampingOnlineTheme() {
                    ConfirmationDetails()
                }
            }
        }
    }

    @Composable
    fun ConfirmationDetails() {
        BoxWithConstraints {
            Surface {
                Column {
                    Title("Confirmation")
                    BookingProperties(label = "Camping Spot", value = "Miller's Point")
                    BookingProperties(label = "Amount of  Nights", value = "2")
                    BookingProperties(label = "Dates", value = "27th January - 29th January")
                    BookingProperties(label = "Address", value = "1077 Valencia Street, Cape Town")

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            modifier = Modifier.padding(16.dp),
                            onClick = { findNavController().navigate(R.id.paymentFragment_dest) },
                        ) {
                            Text(text = "Make payment now")
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun Title(
        name: String
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                    top = 16.dp
                )
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
        }
    }

    @Composable
    fun BookingProperties(label: String, value: String) {
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
            Divider(modifier = Modifier.padding(bottom = 4.dp))
            Text(
                text = label,
                modifier = Modifier.height(24.dp),
                style = MaterialTheme.typography.caption,
            )
            Text(
                text = value,
                modifier = Modifier.height(24.dp),
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Visible
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ConfirmationDetailsPreview() {
        ConfirmationDetails()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ConfirmationDetailsFragment()
    }
}
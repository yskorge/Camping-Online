package com.example.android.campingonline

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.fragment.navArgs
import com.example.android.campingonline.places.CampingSpotDetails
import com.example.android.campingonline.ui.theme.BasicsCampingOnlineTheme
import androidx.navigation.fragment.findNavController

class CampingDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val safeArgs: CampingDetailsFragmentArgs by navArgs()
        val campingSpotId = safeArgs.campingSpotId

        return ComposeView(requireContext()).apply {
            setContent {
                BasicsCampingOnlineTheme() {
                    // TODO set our theme for the app
                    CampingDetails(campingSpotId)
                }
            }
        }
    }

    @Composable
    fun CampingDetails(campingSpotId: String) {
        val scrollState = rememberScrollState()

        BoxWithConstraints {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    // Image header
                    CampingDetailsHeader(
                        campingSpotDetails = CampingSpotDetails(
                            campingSpotId,
                            "Miller's Point",
                            R.drawable.caming_image_1
                        ),
                        containerHeight = this@BoxWithConstraints.maxHeight
                    )

                    // Content
                    Content(
                        "Miller's Point",
                        this@BoxWithConstraints.maxHeight,
                        this@BoxWithConstraints.maxWidth
                    )
                }
            }
        }
    }

    @Composable
    fun CampingDetailsHeader(campingSpotDetails: CampingSpotDetails, containerHeight: Dp) {
        Image(
            modifier = Modifier
                .heightIn(max = containerHeight / 2)
                .fillMaxWidth(),
            painter = painterResource(id = campingSpotDetails.imageId),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }


    @Composable
    private fun Title(
        name: String
    ) {
        Column(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp,
                top = 16.dp
            )
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
        }
    }

    @Composable
    fun CampingProperties(label: String, value: String) {
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

    @Composable
    private fun Content(name: String, containerHeight: Dp, containerWidth: Dp) {
        Column {
            Title(name)
            CampingProperties("Rating", "4.5")
            CampingProperties("Description", "Description Description")
            CampingProperties("Address", "1077 Valencia Street, Cape Town")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { findNavController().navigate(R.id.calendarFragment_dest) },
                modifier = Modifier
                    .widthIn(containerWidth / 2)
            ) {
                Text(text = "Book now")
            }
        }

        Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }

    // TODO make the button reusable
//    @Composable
//    fun ButtonStandart(containerWidth: Dp, navAction: () -> Unit) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Button(
//                onClick = { navAction() },
//                modifier = Modifier
//                    .widthIn(containerWidth / 2)
//            ) {
//                Text(text = "Book now")
//            }
//        }
//    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultCampingDetailsPreview() {
        CampingDetails("1")
    }

    // TODO need to be set up with proper app theme
    @Preview(
        showBackground = true,
        uiMode = Configuration.UI_MODE_NIGHT_YES,
    )
    @Composable
    fun DarkCampingDetailsPreview() {
        CampingDetails("1")
    }
}
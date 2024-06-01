package com.adevinta.mappicker

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Location
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adevinta.leku.ADDRESS
import com.adevinta.leku.LATITUDE
import com.adevinta.leku.leku_POI
import com.adevinta.leku.LOCATION_ADDRESS
import com.adevinta.leku.LONGITUDE
import com.adevinta.leku.lekuPoi
import com.adevinta.leku.LocationPicker
import com.adevinta.leku.LocationPickerActivity
import com.adevinta.leku.TIME_ZONE_DISPLAY_NAME
import com.adevinta.leku.TIME_ZONE_ID
import com.adevinta.leku.TRANSITION_BUNDLE
import com.adevinta.leku.ZIPCODE
import com.adevinta.leku.tracker.LocationPickerTracker
import com.adevinta.leku.tracker.TrackEvents

 const val DEMO_LATITUDE = 41.4036299
 const val DEMO_LONGITUDE = 2.1743558

public class MainActivity : AppCompatActivity() {

    val lekuActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                onResult(result.data)
            } else {
                Log.d("RESULT****", "CANCELLED")
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build()
        )
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build())

        setContent {

            MainView()
        }

        initializeLocationPickerTracker()
    }

     fun onResult(data: Intent?) {
        Log.d("RESULT****", "OK")
        val latitude = data?.getDoubleExtra(LATITUDE, 0.0)
        Log.d("LATITUDE****", latitude.toString())
        val longitude = data?.getDoubleExtra(LONGITUDE, 0.0)
        Log.d("LONGITUDE****", longitude.toString())
        val address = data?.getStringExtra(LOCATION_ADDRESS)
        Log.d("ADDRESS****", address.toString())
        val postalcode = data?.getStringExtra(ZIPCODE)
        Log.d("POSTALCODE****", postalcode.toString())
        val bundle = data?.getBundleExtra(TRANSITION_BUNDLE)
        Log.d("BUNDLE TEXT****", bundle?.getString("test").toString())
        val fullAddress = data?.getParcelableExtra<Address>(ADDRESS)
        if (fullAddress != null) {
            Log.d("FULL ADDRESS****", fullAddress.toString())
        }
        val timeZoneId = data?.getStringExtra(TIME_ZONE_ID)
        if (timeZoneId != null) {
            Log.d("TIME ZONE ID****", timeZoneId)
        }
        val timeZoneDisplayName = data?.getStringExtra(TIME_ZONE_DISPLAY_NAME)
        if (timeZoneDisplayName != null) {
            Log.d("TIME ZONE NAME****", timeZoneDisplayName)
        }
    }


     fun initializeLocationPickerTracker() {
        LocationPicker.setTracker(MyPickerTracker(this))
    }

     class MyPickerTracker( val context: Context) : LocationPickerTracker {
        override fun onEventTracked(event: TrackEvents) {
            Toast.makeText(context, "Event: " + event.eventName, Toast.LENGTH_SHORT).show()
        }
    }
}



 fun onLegacyMapClicked(context: Context) {
    val activity = context
    val locationPickerIntent = LocationPickerActivity.Builder(activity)
        .withLocation(DEMO_LATITUDE, DEMO_LONGITUDE)
        .withUnnamedRoadHidden()
        .withLegacyLayout()
        .build()

     activity.startActivity(locationPickerIntent)
}



@Composable
@Preview(showBackground = true)
fun MainView() {
    val context = LocalContext.current

    Column(
        Modifier
            .padding(16.dp, 40.dp, 16.dp, 0.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            painter = painterResource(id = R.mipmap.logo_pride),
            contentDescription = null
        )
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(context.resources.getColor(R.color.leku_app_blue)),
                contentColor = Color.White
            ),
            onClick = {
                onLegacyMapClicked(context)
            }
        ) {
            Text(
                stringResource(id = R.string.launch_legacy_map_picker),
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }



    }
}

//package scholl.sptech.pridepoints.front
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import com.mapbox.maps.MapInitOptions
//import com.mapbox.maps.MapView
//import com.mapbox.maps.Style
//import com.mapbox.maps.dsl.cameraOptions
//import com.mapbox.maps.plugin.delegates.listeners.OnMapLoadedListener
//import com.mapbox.geojson.Point
//import com.mapbox.maps.plugin.animation.MapAnimationOptions
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MapboxMapView()
//        }
//    }
//
//    @Composable
//    fun MapboxMapView() {
//        val mapView = MapView(
//            context = this@MainActivity,
//            mapInitOptions = MapInitOptions(context = this@MainActivity)
//        )
//
//        mapView.apply {
//            getMapboxMap().addOnMapLoadedListener(object : OnMapLoadedListener {
//                override fun onMapLoaded() {
//                    loadStyleUri(Style.MAPBOX_STREETS) {
//                        // Once the style is loaded, animate the camera
//                        val cameraOptions = cameraOptions {
//                            center(Point.fromLngLat(-98.0, 39.5))
//                            zoom(2.0)
//                            pitch(0.0)
//                            bearing(0.0)
//                        }
//                        mapView.getAnimationPlugin().easeTo(
//                            cameraOptions,
//                            MapAnimationOptions.mapAnimationOptions {
//                                duration(1000)
//                            }
//                        )
//                    }
//                }
//            })
//        }
//    }
//}

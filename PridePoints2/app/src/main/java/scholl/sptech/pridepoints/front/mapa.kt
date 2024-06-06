package scholl.sptech.pridepoints.front


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.geojson.Point
import scholl.sptech.pridepoints.front.avaliacao.EmpresaScreen
import scholl.sptech.pridepoints.ui.theme.PridePointsTheme

class MapsActivity : AppCompatActivity() {

    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS) { setupMap() }
    }

    private fun setupMap() {
        val pointAnnotationManager = mapView.annotations.createPointAnnotationManager()

        val point = Point.fromLngLat(151.0, -34.0)
        val pointAnnotationOptions = PointAnnotationOptions()
            .withPoint(point)
            .withIconImage("marker-15")

        pointAnnotationManager.create(pointAnnotationOptions)
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
@Preview()
@Composable
fun DefaultPreview() {
    PridePointsTheme {
    
    }
}

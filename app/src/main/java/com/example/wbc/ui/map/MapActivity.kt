package com.example.wbc.ui.map

import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.wbc.databinding.ActivityMapBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import java.util.*

class MapActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityMapBinding

    private val mapViewModel: MapViewModel by viewModels()

    private val mapView by lazy {
        MapView(this)
    }

    private val marker = MapPOIItem()

    private val permissionList = arrayOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
    )

    private val requestMultiplePermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            result.forEach {
                if (!it.value) {
                    Toast.makeText(this, "${it.key}권한 허용 필요", Toast.LENGTH_SHORT).show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestMultiplePermission.launch(permissionList)

        if (intent.hasExtra("address")) {
            val address = intent.getStringExtra("address")
            val location = searchLocation(address!!)
            setMarker(location.latitude, location.longitude, address)
        }

        val mapViewContainer = binding.mapView as ViewGroup
        mapViewContainer.addView(mapView)

        // 현재 위치로 이동
        binding.fabMap.setOnClickListener {
            getMyLocation()
        }

        binding.btnSearch.setOnClickListener {
            if (binding.editSearch.text.toString().isBlank()) {
                Toast.makeText(this, "주소를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                val location = searchLocation(binding.editSearch.text.toString())
                setMarker(location.latitude, location.longitude, binding.editSearch.text.toString())
            }
        }
    }

    private fun getMyLocation() {
        val locationProvider = LocationProvider(this)
        val latitude = locationProvider.getLocationLatitude()
        val longitude = locationProvider.getLocationLongitude()

        setMarker(latitude, longitude, "현재 위치")

    }

    private fun searchLocation(address: String) : Location {
        return try {
            Geocoder(this, Locale.KOREA).getFromLocationName(address, 1)?.let {
                Location("").apply {
                    latitude = it[0].latitude
                    longitude = it[0].longitude
                }
            } ?: Location("").apply {
                latitude = 0.0
                longitude = 0.0
            }
        } catch (e: Exception) {
            e.printStackTrace()
            searchLocation(address)
        }
    }

    private fun setMarker(latitude: Double, longitude: Double, title: String) {
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);
        marker.itemName = title
        marker.mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)
        marker.markerType = MapPOIItem.MarkerType.BluePin
        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin

        mapView.addPOIItem(marker)
    }

}
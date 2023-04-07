package com.example.wbc.ui.map

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.wbc.databinding.ActivityMapBinding
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MapActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityMapBinding

    private val mapViewModel: MapViewModel by viewModels()

    private val mapView by lazy {
        MapView(this)
    }

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


        val mapViewContainer = binding.mapView as ViewGroup
        mapViewContainer.addView(mapView)

        // 현재 위치로 이동
        binding.fabMap.setOnClickListener {
            getMyLocation()
        }
    }

    private fun getMyLocation() {
        val locationProvider = LocationProvider(this)
        val latitude = locationProvider.getLocationLatitude()
        val longitude = locationProvider.getLocationLongitude()
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);
    }


}
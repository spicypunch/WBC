package com.example.wbc.ui.map

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.wbc.R
import com.example.wbc.databinding.ActivityMapBinding
import com.example.wbc.di.coroutine.IoDispatcher
import com.example.wbc.ui.bus_info.BusArrivalActivity
import com.example.wbc.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import javax.inject.Inject

@AndroidEntryPoint
class MapActivity() : AppCompatActivity() {
    @Inject
    @IoDispatcher
    lateinit var ioDispatcher: CoroutineDispatcher
    private lateinit var binding: ActivityMapBinding
    private val mapViewModel: MapViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()
    private val eventListener = MarkerEventListener(this)

    // 위도
    private var latitude: Double = 0.0

    // 경도
    private var longitude: Double = 0.0
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
        // 권한 확인
        requestMultiplePermission.launch(permissionList)

        // 카카오맵 띄우기
        val mapViewContainer = binding.mapView as ViewGroup
        mapViewContainer.addView(mapView)
        mapView.setPOIItemEventListener(eventListener)

        // 현재 위치
        getMyLocation()

        // 현재 위치 근방의 버스 정류소 출력
        mapViewModel.getBusStationInfo(latitude, longitude)

        // Search Fragment에서 넘어온 값 처리
        intent.getStringExtra("address")?.let {
            mapViewModel.searchLocation(it, latitude, longitude)
//            setMarker(latitude, longitude, it, null)
        }

        // 현재 위치로 이동 버튼
        binding.fabMap.setOnClickListener {
            getMyLocation()
        }
        // 검색 버튼
        binding.btnSearch.setOnClickListener {
            if (binding.editSearch.text.toString().isBlank()) {
                Toast.makeText(this, "주소를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                mapViewModel.searchLocation(binding.editSearch.text.toString(), latitude, longitude)
                // 검색 기록 추가
                searchViewModel.insertHistory(binding.editSearch.text.toString())
            }
        }
        // 검색 결과
        mapViewModel.searchResult.observe(this, androidx.lifecycle.Observer {
            if (it.documents.isEmpty()) {
                Toast.makeText(this, "5km 근방에 찾으신 시설이 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
//                setMarker(
//                    it.documents[0].y.toDouble(),
//                    it.documents[0].x.toDouble(),
//                    it.documents[0].place_name,
//                    null
//                )
                mapViewModel.getBusStationInfo(
                    it.documents[0].y.toDouble(),
                    it.documents[0].x.toDouble()
                )
            }
        })
        // 버스 정류장 출력
        mapViewModel.busStationResult.observe(this, androidx.lifecycle.Observer {
            if (it.body?.items?.item?.isEmpty() == true) {
                Toast.makeText(this, "근처에 버스 정류장이 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                for (i in it.body?.items?.item!!) {
                    setMarker(i.gpsLati!!, i.gpsLong!!, i.nodeNm!!, i.nodeId!!.substring(3))
                }
            }
        })
    }

    private fun getMyLocation() {
        val locationProvider = LocationProvider(this)
        latitude = locationProvider.getLocationLatitude()
        longitude = locationProvider.getLocationLongitude()

        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);

//        setMarker(latitude, longitude, "현재 위치", null)
    }

    // 지도에 마커 생성
    private fun setMarker(latitude: Double, longitude: Double, title: String, stationID: String?) {
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);
        marker.apply {
            if (stationID != null) {
                itemName = "$title / $stationID"
            } else {
                itemName = title
            }
            mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)
            markerType = MapPOIItem.MarkerType.BluePin
            selectedMarkerType = MapPOIItem.MarkerType.RedPin
        }
        mapView.addPOIItem(marker)
    }

//    private fun setBusStopMarker(
//        latitude: Double,
//        longitude: Double,
//        title: String,
//        stationID: String
//    ) {
////        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);
//        marker.apply {
//            itemName = "$title / $stationID"
//            isShowCalloutBalloonOnTouch
//            mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)
//            markerType = MapPOIItem.MarkerType.CustomImage
//            selectedMarkerType = MapPOIItem.MarkerType.CustomImage
//            customImageResourceId = R.drawable.icon_bus
//            customSelectedImageResourceId = R.drawable.icon_bus
//            isCustomImageAutoscale = false
//            setCustomImageAnchor(0.5f, 1.0f)
//        }
//        mapView.addPOIItem(marker)
//    }
}